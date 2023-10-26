package kr.nagaza.nagazaserver.infrastructure.restapi

import com.fasterxml.jackson.databind.ObjectMapper
import com.nimbusds.jose.jwk.JWK
import io.jsonwebtoken.Jwts
import kr.nagaza.nagazaserver.domain.exception.DomainException
import kr.nagaza.nagazaserver.domain.exception.OAuthLoginFailedException
import kr.nagaza.nagazaserver.domain.model.SocialProvider
import kr.nagaza.nagazaserver.domain.model.SocialUser
import kr.nagaza.nagazaserver.domain.repository.OAuthProvider
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

@Component("appleOAuthProvider")
class AppleOAuthProvider(
    @Qualifier("oAuthClient") private val webClient: WebClient,
    private val objectMapper: ObjectMapper,
) : OAuthProvider {
    override fun authenticate(provider: SocialProvider, accessToken: String): SocialUser {
        return webClient.get()
            .uri("https://appleid.apple.com/auth/keys")
            .headers {
                it.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
            }
            .retrieve()
            .bodyToMono(KeyResponse::class.java)
            .map {
                val header = accessToken.split("\\.".toRegex()).toTypedArray()[0]
                val decodedHeader = String(Base64.getDecoder().decode(header))
                val parsedHeader = objectMapper.readValue(decodedHeader, JWTTokenHeader::class.java)
                val key = it?.keys?.first { it.kid == parsedHeader.kid }
                val keyData = JWK.parse(ObjectMapper().writeValueAsString(key)).toRSAKey().toRSAPublicKey()
                val parsed = Jwts.parserBuilder()
                    .setSigningKey(keyData)
                    .build()
                    .parseClaimsJws(accessToken)

                SocialUser(
                    identifier = parsed.body["sub"] as String,
                    provider = SocialProvider.APPLE,
                    userId = ""
                )
            }.block() ?: throw OAuthLoginFailedException()
    }

    data class Key(val kty: String, val kid: String, val use: String, val alg: String, val n: String, val e: String)
    data class KeyResponse(
        val keys: List<Key>,
    )

    data class JWTTokenHeader(var kid: String, val alg: String)
}
