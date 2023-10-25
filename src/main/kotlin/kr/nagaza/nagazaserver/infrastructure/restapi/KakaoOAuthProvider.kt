package kr.nagaza.nagazaserver.infrastructure.restapi

import kr.nagaza.nagazaserver.domain.exception.DomainException
import kr.nagaza.nagazaserver.domain.model.SocialProvider
import kr.nagaza.nagazaserver.domain.model.SocialUser
import kr.nagaza.nagazaserver.domain.repository.OAuthProvider
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

@Component("kakaoOAuthProvider")
class KakaoOAuthProvider(
    @Qualifier("oAuthClient") private val webClient: WebClient,
) : OAuthProvider {
    override fun authenticate(provider: SocialProvider, accessToken: String): SocialUser {
        return webClient.post()
            .uri("https://kapi.kakao.com/v2/user/me")
            .headers {
                it.setBearerAuth(accessToken)
            }
            .retrieve()
            .bodyToMono(KakaoUserResponse::class.java)
            .map {
                SocialUser(
                    identifier = it.id.toString(),
                    provider = SocialProvider.KAKAO,
                    userId = ""
                )
            }.block() ?: throw DomainException()
    }

    data class KakaoUserResponse(val id: Long, val connected_at: String, val kakao_account: KakaoEmailAccount?)
    data class KakaoEmailAccount(
        val email: String?,
        val email_needs_agreement: Boolean?,
        val is_email_valid: Boolean?,
        val is_email_verified: Boolean?,
    )
}
