package kr.nagaza.nagazaserver.presenter.restapi.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import kr.nagaza.nagazaserver.domain.exception.DomainException
import kr.nagaza.nagazaserver.domain.exception.NotValidInputException
import kr.nagaza.nagazaserver.domain.model.SocialProvider
import kr.nagaza.nagazaserver.domain.service.SocialLoginService
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
class AuthController(
    private val socialLoginService: SocialLoginService,
) {
    @Operation(summary = "소셜로그인", description = "소셜 로그인 후 토큰을 발급합니다.")
    @PostMapping
    fun requestSocialLogin(
        @RequestBody @Valid request: SocialLoginRequest,
    ): SocialLoginResponse {
        val provider = SocialProvider.fromString(request.provider)
            ?: throw NotValidInputException(optional = "provider not valid")
        val result = socialLoginService.socialLogin(
            provider = provider,
            accessToken = request.accessToken,
        )
        return SocialLoginResponse(
            accessToken = result.token.accessToken,
            refreshToken = result.token.refreshToken,
            isFirstLogin = result.isFirstLogin,
        )
    }

    @Operation(summary = "토큰 갱신", description = "토큰을 갱신합니다.")
    @PostMapping("/refresh")
    fun refreshToken(
        @RequestBody @Valid request: RefreshTokenRequest,
    ): SocialLoginResponse {
        val result = socialLoginService.refreshToken(request.refreshToken)
        return SocialLoginResponse(
            accessToken = result.token.accessToken,
            refreshToken = result.token.refreshToken,
            isFirstLogin = result.isFirstLogin,
        )
    }
}
