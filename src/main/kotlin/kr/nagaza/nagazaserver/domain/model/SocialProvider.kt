package kr.nagaza.nagazaserver.domain.model

enum class SocialProvider {
    APPLE, KAKAO, NAVER;

    companion object {
        fun fromString(provider: String): SocialProvider? {
            return when (provider) {
                "apple" -> APPLE
                "kakao" -> KAKAO
                "naver" -> NAVER
                else -> null
            }
        }
    }
}
