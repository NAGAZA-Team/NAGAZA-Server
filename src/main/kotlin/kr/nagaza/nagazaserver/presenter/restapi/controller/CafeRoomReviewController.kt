package kr.nagaza.nagazaserver.presenter.restapi.controller

import kr.nagaza.nagazaserver.domain.service.CafeRoomReviewService
import kr.nagaza.nagazaserver.presenter.restapi.api.CafeRoomReviewApi
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomReviewResponse
import org.springframework.stereotype.Controller

@Controller
class CafeRoomReviewController(
    private val cafeRoomReviewService: CafeRoomReviewService,
): CafeRoomReviewApi {
    override fun getReviews(cafeId: String, roomId: String): List<CafeRoomReviewResponse> {
        return cafeRoomReviewService.getAllByRoomId(
            roomId = roomId,
        ).map {
            CafeRoomReviewResponse.fromModel(it)
        }
    }
}
