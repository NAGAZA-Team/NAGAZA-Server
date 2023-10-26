package kr.nagaza.nagazaserver.infrastructure.restapi

import kr.nagaza.nagazaserver.domain.model.SocialProvider
import kr.nagaza.nagazaserver.domain.model.SocialUser
import kr.nagaza.nagazaserver.domain.repository.OAuthProvider
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Primary
@Component
class RestOAuthProvider(
    @Qualifier("appleOAuthProvider") private val appleOAuthProvider: OAuthProvider,
    @Qualifier("kakaoOAuthProvider") private val kakaoOAuthProvider: OAuthProvider,
): OAuthProvider {
    override fun authenticate(provider: SocialProvider, accessToken: String): SocialUser {
        val socialProvider = mapProvider(provider)
        return socialProvider.authenticate(
            provider = provider,
            accessToken = accessToken,
        )
    }

    private fun mapProvider(provider: SocialProvider) = when(provider) {
        SocialProvider.APPLE -> appleOAuthProvider
        SocialProvider.KAKAO -> kakaoOAuthProvider
        else -> throw IllegalArgumentException()
    }
}
