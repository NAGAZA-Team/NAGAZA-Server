package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import kr.nagaza.nagazaserver.domain.model.rating.RoadType
import java.util.*

@Schema()
data class CafeRoomReviewResponse(
    val reviewId: String,
    val userId: String,
    val fields: List<RatingFieldResponse>,
    val rating: Double,
    val roadType: RoadType,
    val createdAt: Date,
    val content: String?,
)
