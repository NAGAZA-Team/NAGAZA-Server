package kr.nagaza.nagazaserver.domain.model

data class SocialLoginResult(
    val token: AuthToken,
    val isFirstLogin: Boolean,
)
