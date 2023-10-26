package kr.nagaza.nagazaserver.domain.repository

import kr.nagaza.nagazaserver.domain.model.CafeRoomReview

interface CafeRoomReviewRepository {
    fun getAllByUserId(userId: String): List<CafeRoomReview>
    fun getAllByRoomId(roomId: String): List<CafeRoomReview>
}
