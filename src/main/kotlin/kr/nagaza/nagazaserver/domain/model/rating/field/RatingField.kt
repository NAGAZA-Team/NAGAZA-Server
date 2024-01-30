package kr.nagaza.nagazaserver.domain.model.rating.field

import InvalidRatingValueException

data class RatingField(
    val type: RatingFieldType,
    val value: Int,
) {
    init {
        if (!getConstraint(type).isInRange(value)) {
            throw InvalidRatingValueException()
        }
    }
}
