package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import kr.nagaza.nagazaserver.infrastructure.jpa.entity.CafeRoomEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface JpaCafeRoomRepository : JpaRepository<CafeRoomEntity, String> {
    fun findAllByCafeId(cafeId: String): List<CafeRoomEntity>

    @Query(
        """
    SELECT cr FROM cafe_room cr 
    JOIN cafe c ON cr.cafeId = c.cafeId
    LEFT JOIN cr.genre g
    WHERE (
     (:queryString IS null OR cr.title LIKE %:queryString% ) AND
     (:genre IS null OR g.genreId = :genre) AND
     (:address1 IS null OR c.addressOne = :address1) AND
     (:address2 IS null OR c.addressTwo = :address2) AND
     (:cafeId IS null OR cr.cafeId = :cafeId)
    )
    """,
    )
    fun search(
        @Param("queryString") queryString: String?,
        @Param("genre") genre: String?,
        @Param("address1") address1: String?,
        @Param("address2") address2: String?,
        @Param("cafeId") cafeId: String?,
    ): List<CafeRoomEntity>
}
