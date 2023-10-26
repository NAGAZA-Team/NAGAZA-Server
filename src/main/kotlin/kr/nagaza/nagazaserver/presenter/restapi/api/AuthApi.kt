package kr.nagaza.nagazaserver.presenter.restapi.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.RefreshTokenRequest
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.SocialLoginRequest
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.SocialLoginResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "인증 API", description = "인증 관련 API")
@RestController
@RequestMapping("/v1/auth")
interface AuthApi {
    @Operation(summary = "소셜로그인", description = "소셜 로그인 후 토큰을 발급합니다.")
    @PostMapping
    fun requestSocialLogin(
        @RequestBody @Valid request: SocialLoginRequest,
    ): SocialLoginResponse

    @Operation(summary = "테스트용 토큰 발급", description = "테스트용 토큰을 발급합니다.")
    @PostMapping("/mock")
    fun requestMockLogin(): SocialLoginResponse

    @Operation(summary = "토큰 갱신", description = "토큰을 갱신합니다.")
    @PostMapping("/refresh")
    fun refreshToken(
        @RequestBody @Valid request: RefreshTokenRequest,
    ): SocialLoginResponse
}
