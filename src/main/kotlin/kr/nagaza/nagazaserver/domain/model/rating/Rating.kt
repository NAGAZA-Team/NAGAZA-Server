package kr.nagaza.nagazaserver.domain.model.rating

import kr.nagaza.nagazaserver.domain.model.rating.field.RatingField
import kr.nagaza.nagazaserver.domain.model.rating.field.RatingFieldType
import kr.nagaza.nagazaserver.domain.model.rating.field.getConstraint

class Rating(
    val fields: Map<RatingFieldType, RatingField>,
) {
    companion object {
        fun builder(): RatingBuilder {
            return RatingBuilder()
        }
    }

    val sum: Double
        get() {
            return fields
                .values.map { it.value * getConstraint(it.type).weight }
                .sum()
                .let {
                    if (it >= 10.0) 10.0 else it
                }
        }

    fun getRoadType(): RoadType {
        if (sum in 0.0..2.0) {
            return RoadType.MUD_ROAD
        } else if (sum in 2.1..4.0) {
            return RoadType.DIRT_ROAD
        } else if (sum in 4.1..6.0) {
            return RoadType.GRASS_ROAD
        } else if (sum in 6.1..8.0) {
            return RoadType.FLOWER_ROAD
        } else {
            return RoadType.FULL_OF_FLOWER_ROAD
        }
    }
}
