package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import kr.nagaza.nagazaserver.infrastructure.jpa.entity.CafeRoomEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JpaCafeRoomRepository : JpaRepository<CafeRoomEntity, String> {
    fun findAllByCafeId(cafeId: String): List<CafeRoomEntity>

    @Query("SELECT DISTINCT cafe_room.genre FROM cafe_room")
    fun findAllGenres(): List<String>
}
