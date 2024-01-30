package kr.nagaza.nagazaserver.domain.model

import kr.nagaza.nagazaserver.domain.model.rating.Rating
import java.util.*

data class CafeRoomReview(
    val reviewId: String,
    val roomId: String,
    val userId: String,
    val detail: CafeRoomReviewDetail,
    val rating: Rating,
    val createdAt: Date,
)

data class CafeRoomReviewDetail(
    val reviewId: String,
    val userCnt: Int,
    val isCleared: Boolean,
    val usedHintCnt: Int,
    val content: String?,
)
