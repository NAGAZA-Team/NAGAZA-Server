package kr.nagaza.nagazaserver.infrastructure.jwt

import kr.nagaza.nagazaserver.domain.model.AuthToken
import kr.nagaza.nagazaserver.domain.repository.TokenProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtTokenProvider(
    @Value("\${app.token.secret}") val secret: String,
    @Value("\${app.token.expiration.access}") val accessTokenExpiration: Long,
    @Value("\${app.token.expiration.refresh}") val refreshTokenExpiration: Long,
): TokenProvider {
    override fun generateToken(userId: String): AuthToken {
        TODO("Not yet implemented")
    }

    override fun isTokenValid(token: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getUserIdFromToken(token: String): String {
        TODO("Not yet implemented")
    }
}
