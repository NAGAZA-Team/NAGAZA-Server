package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import kr.nagaza.nagazaserver.domain.model.CafeRoomReview

data class CafeRoomReviewResponse(
    val reviewId: String,
) {
    companion object {
        fun fromModel(model: CafeRoomReview) = CafeRoomReviewResponse(
            reviewId = model.reviewId,
        )
    }
}
