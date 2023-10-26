package kr.nagaza.nagazaserver.infrastructure.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import kr.nagaza.nagazaserver.domain.model.AuthToken
import kr.nagaza.nagazaserver.domain.repository.TokenProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.lang.Exception
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JwtTokenProvider(
    @Value("\${app.token.secret}") private val secret: String,
    @Value("\${app.token.expiration.access}") private val accessTokenExpiration: Long,
    @Value("\${app.token.expiration.refresh}") private val refreshTokenExpiration: Long,
): TokenProvider {
    private lateinit var signKey: Key
    init {
        val bytes = secret.toByteArray()
        signKey = SecretKeySpec(bytes, SignatureAlgorithm.HS256.jcaName)
    }

    override fun generateToken(userId: String): AuthToken {
        val accessToken = generateAccessToken(userId)
        val refreshToken = generateRefreshToken(userId)
        return AuthToken(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }

    override fun isTokenValid(token: String): Boolean {
        return try{
            Jwts.parserBuilder().setSigningKey(signKey).build()
                .parseClaimsJws(token).body["id"] as String
            true
        } catch(e: Exception) {
            false
        }
    }

    override fun getUserIdFromToken(token: String): String {
        return Jwts.parserBuilder().setSigningKey(signKey).build()
            .parseClaimsJws(token).body["id"] as String
    }

    private fun generateAccessTokenExpiration() = Date(System.currentTimeMillis() + this.accessTokenExpiration * 1000)
    private fun generateRefreshTokenExpiration() = Date(System.currentTimeMillis() + this.refreshTokenExpiration * 1000)

    private fun generateAccessToken(userId: String): String {
        return Jwts.builder()
            .setHeader(buildHeader())
            .setClaims(mapOf("id" to userId))
            .setExpiration(generateAccessTokenExpiration())
            .signWith(signKey, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun generateRefreshToken(userId: String): String {
        return Jwts.builder()
            .setHeader(buildHeader())
            .setClaims(mapOf("id" to userId))
            .setExpiration(generateRefreshTokenExpiration())
            .signWith(signKey, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun buildHeader() =
        mapOf<String, Any>(
            Pair("typ", "JWT"),
            Pair("alg", "HS256"),
            Pair("regDate", System.currentTimeMillis())
        )
}
