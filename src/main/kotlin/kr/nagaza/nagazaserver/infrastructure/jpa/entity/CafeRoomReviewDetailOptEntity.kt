package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "cafe_room_review_det_opt")
class CafeRoomReviewDetailOptEntity(
    @Id
    @Column(name = "review_id")
    val reviewId: String,

    @Column(name = "option_type")
    val optionType: String,

    @Column(name = "option_value")
    val optionValue: Boolean,
)
