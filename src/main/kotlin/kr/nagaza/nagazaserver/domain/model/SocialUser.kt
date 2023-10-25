package kr.nagaza.nagazaserver.domain.model

data class SocialUser(
    val provider: SocialProvider,
    val identifier: String,
    val userId: String,
)
