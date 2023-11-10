package kr.nagaza.nagazaserver.presenter.restapi.controller

import kr.nagaza.nagazaserver.domain.service.CafeRoomReviewService
import kr.nagaza.nagazaserver.domain.service.UserService
import kr.nagaza.nagazaserver.presenter.restapi.api.MeApi
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.UpdateNicknameRequest
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.UpdateProfileImageUrlRequest
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomReviewResponse
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.MeResponse
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.MeSummaryResponse
import org.springframework.stereotype.Controller

@Controller
class MeController(
    private val userService: UserService,
    private val cafeRoomReviewService: CafeRoomReviewService,
) : MeApi {
    override fun getMe(
        userId: String,
    ): MeResponse {
        val me = userService.getUserInfo(userId)
        return me.let(MeResponse::fromModel)
    }

    override fun getMyReviews(userId: String): List<CafeRoomReviewResponse> {
        return cafeRoomReviewService
            .getAllByUserId(userId)
            .map(CafeRoomReviewResponse::fromModel)
    }

    override fun updateNickname(
        userId: String,
        request: UpdateNicknameRequest,
    ): MeResponse {
        val result = userService.updateNickName(
            userId = userId,
            nickname = request.nickname,
        )
        return result.let(MeResponse::fromModel)
    }

    override fun updateProfileImage(
        userId: String,
        request: UpdateProfileImageUrlRequest,
    ): MeResponse {
        val result = userService.updateProfileImageUrl(
            userId = userId,
            profileImageUrl = request.profileImageUrl,
        )
        return result.let(MeResponse::fromModel)
    }

    override fun quitNagaza(userId: String) {
        userService.quitNagaza(userId)
    }

    override fun getMySummary(userId: String): MeSummaryResponse {
        return userService.getUserSummary(userId)
            .let(MeSummaryResponse::fromModel)
    }
}
