package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import kr.nagaza.nagazaserver.domain.model.CafeRoomReview

@Schema(description = "카페 방 리뷰 응답")
data class CafeRoomReviewResponse(
    @Schema(description = "리뷰 아이디", example = "01HDNFJHCNS5E2W35YTB030TJ8")
    val reviewId: String,
) {
    companion object {
        fun fromModel(model: CafeRoomReview) = CafeRoomReviewResponse(
            reviewId = model.reviewId,
        )
    }
}
