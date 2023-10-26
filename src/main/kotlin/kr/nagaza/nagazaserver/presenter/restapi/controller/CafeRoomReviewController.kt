package kr.nagaza.nagazaserver.presenter.restapi.controller

import kr.nagaza.nagazaserver.presenter.restapi.api.CafeRoomReviewApi
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomReviewResponse
import org.springframework.stereotype.Controller

@Controller
class CafeRoomReviewController: CafeRoomReviewApi {
    override fun getReviews(cafeId: String, roomId: String): List<CafeRoomReviewResponse> {
        TODO("Not yet implemented")
    }
}
