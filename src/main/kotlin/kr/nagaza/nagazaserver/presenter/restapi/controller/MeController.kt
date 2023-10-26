package kr.nagaza.nagazaserver.presenter.restapi.controller

import io.swagger.v3.oas.annotations.Operation
import kr.nagaza.nagazaserver.domain.service.UserService
import kr.nagaza.nagazaserver.presenter.restapi.api.MeApi
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.UpdateNicknameRequest
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.UpdateProfileImageUrlRequest
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.MeResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PatchMapping

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

    @Operation(summary = "닉네임 변경", description = "내 닉네임을 변경합니다.")
    @PatchMapping("/nickname")
    override fun updateNickname(
        userId: String,
        request: UpdateNicknameRequest,
    ): MeResponse {
        val result = userService.updateNickName(
            userId = userId,
            nickname = request.nickName,
        )
        return MeResponse.fromModel(result)
    }

    @Operation(summary = "프로필 이미지 변경", description = "내 프로필 이미지를 변경합니다.")
    @PatchMapping("/profile-image")
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
