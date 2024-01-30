package kr.nagaza.nagazaserver.util.mapper.review

import kr.nagaza.nagazaserver.domain.model.CafeRoomReview
import kr.nagaza.nagazaserver.domain.model.rating.field.RatingField
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomReviewResponse
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.RatingFieldResponse

fun RatingField.toDto(): RatingFieldResponse {
    return RatingFieldResponse(
        ratingFieldType = this.type,
        value = this.value,
    )
}

fun CafeRoomReview.toDto(): CafeRoomReviewResponse {
    return CafeRoomReviewResponse(
        reviewId = this.reviewId,
        userId = this.userId,
        fields =
            this.rating.fields.values.map(RatingField::toDto),
        rating = this.rating.sum,
        roadType = this.rating.roadType,
        createdAt = this.createdAt,
        content = this.detail.content,
    )
}
