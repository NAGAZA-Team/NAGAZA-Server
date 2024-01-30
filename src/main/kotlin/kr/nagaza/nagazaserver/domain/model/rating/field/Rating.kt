package kr.nagaza.nagazaserver.domain.model.rating.field

val constraints: Map<RatingFieldType, RatingConstraint> =
    mapOf(
        RatingFieldType.EXPRESSION to RatingConstraint(1, 5, 0.765),
        RatingFieldType.LOCK to RatingConstraint(1, 9, 0.294),
        RatingFieldType.INTERIOR to RatingConstraint(1, 5, 0.412),
        RatingFieldType.ACTIVITY to RatingConstraint(1, 5, 0.235),
        RatingFieldType.DIFFICULTY to RatingConstraint(1, 5, 0.059),
        RatingFieldType.IS_IMPRESSED to RatingConstraint(0, 1, 1.0),
    )

fun getConstraint(type: RatingFieldType): RatingConstraint {
    return constraints[type]!!
}
