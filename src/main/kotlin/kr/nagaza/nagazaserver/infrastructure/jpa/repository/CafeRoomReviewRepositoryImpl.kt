package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import kr.nagaza.nagazaserver.domain.model.CafeRoomReview
import kr.nagaza.nagazaserver.domain.repository.CafeRoomReviewRepository
import org.springframework.stereotype.Repository

@Repository
class CafeRoomReviewRepositoryImpl(
    private val cafeRoomReviewRepository: JpaCafeRoomReviewRepository,
): CafeRoomReviewRepository {
    override fun getAllByUserId(userId: String): List<CafeRoomReview> {
        return cafeRoomReviewRepository
            .findAllByUserId(userId)
            .map { it.toModel() }
    }

    override fun getAllByRoomId(roomId: String): List<CafeRoomReview> {
        return cafeRoomReviewRepository
            .findAllByRoomId(roomId)
            .map { it.toModel() }
    }
}
