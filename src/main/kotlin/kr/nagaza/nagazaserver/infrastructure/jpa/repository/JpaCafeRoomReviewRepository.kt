package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import kr.nagaza.nagazaserver.infrastructure.jpa.entity.CafeRoomReviewEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaCafeRoomReviewRepository : JpaRepository<CafeRoomReviewEntity, String> {
    fun findAllByUserId(userId: String): List<CafeRoomReviewEntity>
    fun findAllByRoomId(roomId: String): List<CafeRoomReviewEntity>
}
