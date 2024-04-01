package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import kr.nagaza.nagazaserver.domain.model.rating.field.RatingFieldType

@Entity(name = "cafe_room_review_rating_field")
class CafeRoomReviewRatingFieldEntity(
    @Id
    @Column(name = "rating_field_id")
    val ratingFieldId: String,
    @Column(name = "review_id", nullable = false)
    val reviewId: String,
    @Column(name = "type", nullable = false)
    val fieldType: RatingFieldType,
    @Column(name = "value", nullable = false)
    val value: Int,
)
