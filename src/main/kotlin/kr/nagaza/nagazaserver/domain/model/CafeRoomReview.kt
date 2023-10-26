package kr.nagaza.nagazaserver.domain.model

data class CafeRoomReview(
    val reviewId: String,
    val roomId: String,
    val userId: String,
    val content: String,
    val detail: CafeRoomReviewDetail,
)

data class CafeRoomReviewDetail(
    val reviewId: String,
    val userCnt: Int?,
    val isCleared: Boolean?,
    val isLifeTheme: Boolean?,
    val usedHintCnt: Int?,
    val difficultyPoint: Int?,
    val activityPoint: Int?,
    val interiorPoint: Int?,
    val productionPoint: Int?,
    val deviceRatio: Double?,
)
