package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import kr.nagaza.nagazaserver.domain.model.rating.RoadType

@Schema(description = "카페 방 리뷰 응답")
data class CafeRoomReviewListResponse(
    val reviewCount: Int,
    val rating: Double,
    val roadType: RoadType,
    val reviewList: List<CafeRoomReviewResponse>,
)
