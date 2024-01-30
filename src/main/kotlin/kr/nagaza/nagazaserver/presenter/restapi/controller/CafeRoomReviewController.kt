package kr.nagaza.nagazaserver.presenter.restapi.controller

import kr.nagaza.nagazaserver.domain.model.CafeRoomReview
import kr.nagaza.nagazaserver.domain.model.rating.Rating
import kr.nagaza.nagazaserver.domain.service.CafeRoomReviewService
import kr.nagaza.nagazaserver.presenter.restapi.api.CafeRoomReviewApi
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomReviewListResponse
import kr.nagaza.nagazaserver.util.mapper.review.toDto
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
            reviewList = reviewList.map(CafeRoomReview::toDto),
        )
    }
}
