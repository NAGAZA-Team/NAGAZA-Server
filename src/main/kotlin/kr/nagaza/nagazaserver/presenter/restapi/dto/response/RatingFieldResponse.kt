package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import kr.nagaza.nagazaserver.domain.model.rating.field.RatingFieldType

@Schema()
data class RatingFieldResponse(
    val ratingFieldType: RatingFieldType,
    val value: Int,
)
