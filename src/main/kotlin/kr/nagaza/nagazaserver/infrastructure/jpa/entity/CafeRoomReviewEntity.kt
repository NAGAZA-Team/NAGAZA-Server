package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.*
import kr.nagaza.nagazaserver.domain.model.CafeRoomReview

@Entity(name = "cafe_room_review")
class CafeRoomReviewEntity(
    @Id
    @Column(name = "review_id")
    val reviewId: String,

    @Column(name = "room_id")
    val roomId: String,

    @Column(name = "user_id")
    val userId: String,

    @Column(name = "content")
    val content: String,

    @OneToOne
    @PrimaryKeyJoinColumn
    val detail: CafeRoomReviewDetailEntity,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    val reviewOpts: MutableSet<CafeRoomReviewDetailOptEntity> = mutableSetOf(),
) {
    fun toModel() = CafeRoomReview(
        reviewId = reviewId,
        roomId = roomId,
        userId = userId,
        content = content,
        detail = detail.toModel(),
    )

    companion object {
        fun fromModel(model: CafeRoomReview) = CafeRoomReviewEntity(
            reviewId = model.reviewId,
            roomId = model.roomId,
            userId = model.userId,
            content = model.content,
            detail = CafeRoomReviewDetailEntity.fromModel(model.detail),
        )
    }
}
