package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import kr.nagaza.nagazaserver.infrastructure.jpa.entity.CafeRoomEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaCafeRoomRepository : JpaRepository<CafeRoomEntity, String> {
    fun findAllByCafeId(cafeId: String): List<CafeRoomEntity>
}
