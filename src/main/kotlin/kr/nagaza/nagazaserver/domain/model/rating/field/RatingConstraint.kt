package kr.nagaza.nagazaserver.domain.model.rating.field

data class RatingConstraint(
    val min: Int,
    val max: Int,
    val weight: Double,
) {
    fun getValue(value: Int): Double {
        return value * weight
    }

    fun isInRange(value: Int): Boolean {
        return value in min..max
    }
}
