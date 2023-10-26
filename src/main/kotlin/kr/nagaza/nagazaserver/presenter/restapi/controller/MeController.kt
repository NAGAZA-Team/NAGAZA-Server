package kr.nagaza.nagazaserver.presenter.restapi.controller

import kr.nagaza.nagazaserver.domain.service.UserService
import kr.nagaza.nagazaserver.presenter.restapi.api.MeApi
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.UpdateNicknameRequest
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.UpdateProfileImageUrlRequest
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.MeResponse
import org.springframework.stereotype.Controller

@Controller
class MeController(
    private val userService: UserService,
) : MeApi {
    override fun getMe(
        userId: String,
    ): MeResponse {
        val me = userService.getMeInfo(userId)
        return MeResponse.fromModel(me)
    }

    override fun updateNickname(
        userId: String,
        request: UpdateNicknameRequest,
    ): MeResponse {
        val result = userService.updateNickName(
            userId = userId,
            nickname = request.nickname,
        )
        return MeResponse.fromModel(result)
    }

    override fun updateProfileImage(
        userId: String,
        request: UpdateProfileImageUrlRequest,
    ): MeResponse {
        val result = userService.updateProfileImageUrl(
            userId = userId,
            profileImageUrl = request.profileImageUrl,
        )
        return MeResponse.fromModel(result)
    }
}
