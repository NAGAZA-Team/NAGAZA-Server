package kr.nagaza.nagazaserver.domain.model

data class User(
    val userId: String,
    val nickname: String,
    val profileImageUrl: String?,
)
