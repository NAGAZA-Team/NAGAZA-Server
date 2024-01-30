package kr.nagaza.nagazaserver.domain.model.rating

import kr.nagaza.nagazaserver.domain.model.rating.field.RatingField
import kr.nagaza.nagazaserver.domain.model.rating.field.RatingFieldType

class RatingBuilder {
    private val fields: MutableMap<RatingFieldType, RatingField> = mutableMapOf()

    fun addRating(field: RatingField): RatingBuilder {
        fields[field.type] = field
        return this
    }

    fun build(): Rating {
        if (fields.size == RatingFieldType.values().size) {
            return Rating(fields)
        } else {
            throw Exception("Not all ratings are set")
        }
    }
}
