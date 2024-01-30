package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "cafe_room_review_det")
class CafeRoomReviewDetailEntity(
    @Id
    @Column(name = "review_id")
    val reviewId: String,
    @Column(name = "user_cnt")
    val userCnt: Int,
    @Column(name = "is_cleared")
    val isCleared: Boolean,
    @Column(name = "is_life_theme")
    val isLifeTheme: Boolean,
    @Column(name = "used_hint_cnt")
    val usedHintCnt: Int,
    @Column(name = "content")
    val content: String,
)
