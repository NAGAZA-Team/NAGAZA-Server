package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "cafe_room_review_det")
class CafeRoomReviewDetailEntity(
    @Id
    @Column(name = "review_id")
    val reviewId: String,
    @Column(name = "user_cnt", nullable = false)
    val userCnt: Int,
    @Column(name = "is_cleared", nullable = false)
    val isCleared: Boolean,
    @Column(name = "is_life_theme", nullable = false)
    val isLifeTheme: Boolean,
    @Column(name = "used_hint_cnt", nullable = false)
    val usedHintCnt: Int,
    @Column(name = "content", nullable = false)
    val content: String,
)
