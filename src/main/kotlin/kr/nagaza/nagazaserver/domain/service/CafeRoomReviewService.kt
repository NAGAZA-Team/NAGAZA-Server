package kr.nagaza.nagazaserver.domain.service

import kr.nagaza.nagazaserver.domain.model.CafeRoomReview
import kr.nagaza.nagazaserver.domain.repository.CafeRoomReviewRepository
import org.springframework.stereotype.Service

@Service
class CafeRoomReviewService(
    private val cafeRoomReviewRepository: CafeRoomReviewRepository,
) {
    fun getAllByUserId(userId: String): List<CafeRoomReview> {
        return cafeRoomReviewRepository.getAllByUserId(userId)
    }

    fun getAllByRoomId(roomId: String): List<CafeRoomReview> {
        return cafeRoomReviewRepository.getAllByRoomId(roomId)
    }
}
