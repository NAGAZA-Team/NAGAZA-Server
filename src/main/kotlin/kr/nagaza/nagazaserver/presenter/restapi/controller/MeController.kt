package kr.nagaza.nagazaserver.presenter.restapi.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import kr.nagaza.nagazaserver.domain.service.UserService
import kr.nagaza.nagazaserver.presenter.restapi.advice.RequestUser
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.UpdateNicknameRequest
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.UpdateProfileImageUrlRequest
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.MeResponse
import org.springframework.web.bind.annotation.*

@Tag(name = "내 정보 API", description = "내 정보 관련 API")
@RestController
@RequestMapping("/v1/me")
class MeController(
    private val userService: UserService,
) {
    @Operation(summary = "내 정보 조회", description = "내 정보를 조회합니다.")
    @GetMapping
    fun getMe(
        @RequestUser userId: String,
    ): MeResponse {
        val me = userService.getMeInfo(userId)
        return MeResponse.fromModel(me)
    }

    @Operation(summary = "닉네임 변경", description = "내 닉네임을 변경합니다.")
    @PatchMapping("/nickname")
    fun updateNickname(
        @RequestUser userId: String,
        @RequestBody @Valid request: UpdateNicknameRequest,
    ): MeResponse {
        val result = userService.updateNickName(
            userId = userId,
            nickname = request.nickName,
        )
        return MeResponse.fromModel(result)
    }

    @Operation(summary = "프로필 이미지 변경", description = "내 프로필 이미지를 변경합니다.")
    @PatchMapping("/profile-image")
    fun updateProfileImage(
        @RequestUser userId: String,
        @RequestBody @Valid request: UpdateProfileImageUrlRequest,
    ): MeResponse {
        val result = userService.updateProfileImageUrl(
            userId = userId,
            profileImageUrl = request.profileImageUrl,
        )
        return MeResponse.fromModel(result)
    }
}
