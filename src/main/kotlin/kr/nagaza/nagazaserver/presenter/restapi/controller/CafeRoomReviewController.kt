package kr.nagaza.nagazaserver.presenter.restapi.controller

import kr.nagaza.nagazaserver.domain.model.rating.Rating
import kr.nagaza.nagazaserver.domain.service.CafeRoomReviewService
import kr.nagaza.nagazaserver.presenter.restapi.api.CafeRoomReviewApi
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomReviewListResponse
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomReviewResponse
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.RatingFieldResponse
import org.springframework.stereotype.Controller

@Controller
class CafeRoomReviewController(
    private val cafeRoomReviewService: CafeRoomReviewService,
) : CafeRoomReviewApi {
    override fun getReviews(
        cafeId: String,
        roomId: String,
    ): CafeRoomReviewListResponse {
        val reviewList =
            cafeRoomReviewService.getAllByRoomId(
                roomId = roomId,
            )

        return CafeRoomReviewListResponse(
            reviewCount = reviewList.size,
            rating = reviewList.map { it.rating.sum }.average(),
            roadType = Rating.getRoadType(reviewList.map { it.rating.sum }.average()),
            reviewList =
                reviewList.map {
                    CafeRoomReviewResponse(
                        reviewId = it.reviewId,
                        userId = it.userId,
                        fields =
                            it.rating.fields.map { field ->
                                RatingFieldResponse(
                                    ratingFieldType = field.key,
                                    value = field.value.value,
                                )
                            },
                        rating = it.rating.sum,
                        roadType = Rating.getRoadType(it.rating.sum),
                        createdAt = it.createdAt,
                        content = it.detail.content,
                    )
                },
        )
    }
}
