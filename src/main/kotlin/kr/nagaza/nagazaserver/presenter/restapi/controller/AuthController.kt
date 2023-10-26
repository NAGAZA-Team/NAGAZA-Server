package kr.nagaza.nagazaserver.presenter.restapi.controller

import kr.nagaza.nagazaserver.domain.exception.NotValidInputException
import kr.nagaza.nagazaserver.domain.model.SocialProvider
import kr.nagaza.nagazaserver.domain.service.SocialLoginService
import kr.nagaza.nagazaserver.presenter.restapi.api.AuthApi
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.RefreshTokenRequest
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.SocialLoginRequest
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.SocialLoginResponse
import org.springframework.stereotype.Controller

@Controller
class AuthController(
    private val socialLoginService: SocialLoginService,
) : AuthApi {
    override fun requestSocialLogin(
        request: SocialLoginRequest,
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

    override fun refreshToken(
        request: RefreshTokenRequest,
    ): SocialLoginResponse {
        val result = socialLoginService.refreshToken(request.refreshToken)
        return SocialLoginResponse(
            accessToken = result.token.accessToken,
            refreshToken = result.token.refreshToken,
            isFirstLogin = result.isFirstLogin,
        )
    }
}
