package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import kr.nagaza.nagazaserver.domain.model.CafeRoomReview
import kr.nagaza.nagazaserver.domain.model.CafeRoomReviewDetail
import kr.nagaza.nagazaserver.domain.model.rating.Rating
import kr.nagaza.nagazaserver.domain.model.rating.field.RatingField
import kr.nagaza.nagazaserver.domain.repository.CafeRoomReviewRepository
import org.springframework.stereotype.Repository

@Repository
class CafeRoomReviewRepositoryImpl(
    private val cafeRoomReviewRepository: JpaCafeRoomReviewRepository,
) : CafeRoomReviewRepository {
    override fun getAllByUserId(userId: String): List<CafeRoomReview> {
        throw NotImplementedError()
    }

    override fun getAllByRoomId(roomId: String): List<CafeRoomReview> {
        val entity =
            cafeRoomReviewRepository
                .findAllByRoomId(roomId)

        return entity.map {
            val rating = Rating.builder()
            it.ratingFields.forEach { field ->
                rating.addRating(
                    RatingField(
                        field.fieldType,
                        field.value,
                    ),
                )
            }

            CafeRoomReview(
                reviewId = it.reviewId,
                roomId = it.roomId,
                userId = it.userId,
                detail =
                    CafeRoomReviewDetail(
                        reviewId = it.reviewId,
                        userCnt = it.detail.userCnt,
                        isCleared = it.detail.isCleared,
                        usedHintCnt = it.detail.usedHintCnt,
                        content = it.detail.content,
                    ),
                rating = rating.build(),
                createdAt = it.createdAt,
            )
        }
    }
}
