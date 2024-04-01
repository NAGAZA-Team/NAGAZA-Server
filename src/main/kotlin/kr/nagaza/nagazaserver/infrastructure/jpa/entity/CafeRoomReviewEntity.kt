package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.*
import java.util.*

@Entity(name = "cafe_room_review")
class CafeRoomReviewEntity(
    @Id
    @Column(name = "review_id")
    val reviewId: String,
    @Column(name = "room_id", nullable = false)
    val roomId: String,
    @Column(name = "user_id", nullable = false)
    val userId: String,
    @OneToOne
    @PrimaryKeyJoinColumn
    val detail: CafeRoomReviewDetailEntity,
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    val ratingFields: List<CafeRoomReviewRatingFieldEntity> = listOf(),
    @Column(name = "created_at", nullable = false)
    val createdAt: Date,
)
