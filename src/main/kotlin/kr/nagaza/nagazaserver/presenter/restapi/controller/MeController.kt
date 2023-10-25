package kr.nagaza.nagazaserver.presenter.restapi.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.MeResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "내 정보 API", description = "내 정보 관련 API")
@RestController
@RequestMapping("/v1/me")
class MeController {
    @Operation(summary = "내 정보 조회", description = "내 정보를 조회합니다.")
    @GetMapping
    fun getMe(): MeResponse {
        TODO()
    }

    @Operation(summary = "닉네임 변경", description = "내 닉네임을 변경합니다.")
    @PatchMapping("/nickname")
    fun updateNickname(): MeResponse {
        TODO()
    }

    @Operation(summary = "프로필 이미지 변경", description = "내 프로필 이미지를 변경합니다.")
    @PatchMapping("/profile-image")
    fun updateProfileImage(): MeResponse {
        TODO()
    }
}
