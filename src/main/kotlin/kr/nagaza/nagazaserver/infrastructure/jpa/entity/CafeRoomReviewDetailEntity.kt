package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import kr.nagaza.nagazaserver.domain.model.CafeRoomReviewDetail

@Entity(name = "cafe_room_review_det")
class CafeRoomReviewDetailEntity(
    @Id
    @Column(name = "review_id")
    val reviewId: String,

    @Column(name = "user_cnt")
    val userCnt: Int?,

    @Column(name = "is_cleared")
    val isCleared: Boolean?,

    @Column(name = "is_life_theme")
    val isLifeTheme: Boolean?,

    @Column(name = "used_hint_cnt")
    val usedHintCnt: Int?,

    @Column(name = "difficulty_point")
    val difficultyPoint: Int?,

    @Column(name = "activity_point")
    val activityPoint: Int?,

    @Column(name = "interior_point")
    val interiorPoint: Int?,

    @Column(name = "production_point")
    val productionPoint: Int?,

    @Column(name = "device_ratio")
    val deviceRatio: Double?,
) {
    fun toModel() = CafeRoomReviewDetail(
        reviewId = reviewId,
        userCnt = userCnt,
        isCleared = isCleared,
        isLifeTheme = isLifeTheme,
        usedHintCnt = usedHintCnt,
        difficultyPoint = difficultyPoint,
        activityPoint = activityPoint,
        interiorPoint = interiorPoint,
        productionPoint = productionPoint,
        deviceRatio = deviceRatio,
    )

    companion object {
        fun fromModel(model: CafeRoomReviewDetail) = CafeRoomReviewDetailEntity(
            reviewId = model.reviewId,
            userCnt = model.userCnt,
            isCleared = model.isCleared,
            isLifeTheme = model.isLifeTheme,
            usedHintCnt = model.usedHintCnt,
            difficultyPoint = model.difficultyPoint,
            activityPoint = model.activityPoint,
            interiorPoint = model.interiorPoint,
            productionPoint = model.productionPoint,
            deviceRatio = model.deviceRatio,
        )
    }
}
