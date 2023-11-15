package kr.nagaza.nagazaserver.infrastructure.restapi

import kr.nagaza.nagazaserver.domain.exception.OAuthLoginFailedException
import kr.nagaza.nagazaserver.domain.model.SocialProvider
import kr.nagaza.nagazaserver.domain.model.SocialUser
import kr.nagaza.nagazaserver.domain.repository.OAuthProvider
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component("naverOAuthProvider")
class NaverOAuthProvider(
    @Qualifier("oAuthClient") private val webClient: WebClient,
) : OAuthProvider {
    override fun authenticate(provider: SocialProvider, accessToken: String): SocialUser {
        TODO()
    }
}
