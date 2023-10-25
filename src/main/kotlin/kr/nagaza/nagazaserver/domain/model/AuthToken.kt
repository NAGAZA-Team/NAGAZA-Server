package kr.nagaza.nagazaserver.domain.model

data class AuthToken(
    val accessToken: String,
    val refreshToken: String,
)
