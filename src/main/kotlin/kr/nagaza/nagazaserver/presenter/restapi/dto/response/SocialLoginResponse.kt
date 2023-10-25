package kr.nagaza.nagazaserver.presenter.restapi.dto.response

data class SocialLoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val isFirstLogin: Boolean,
)
