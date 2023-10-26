package kr.nagaza.nagazaserver.domain.service

import jakarta.transaction.Transactional
import kr.nagaza.nagazaserver.domain.exception.AuthTokenNotValidException
import kr.nagaza.nagazaserver.domain.exception.DomainException
import kr.nagaza.nagazaserver.domain.model.*
import kr.nagaza.nagazaserver.domain.repository.*
import kr.nagaza.nagazaserver.util.MockNickGenerator
import org.springframework.stereotype.Service

@Service
class SocialLoginService(
    private val tokenProvider: TokenProvider,
    private val oAuthProvider: OAuthProvider,
    private val userRepository: UserRepository,
    private val identifierProvider: IdentifierProvider,
    private val socialUserRepository: SocialUserRepository,
) {
    @Transactional
    fun socialLogin(
        provider: SocialProvider,
        accessToken: String,
    ): SocialLoginResult {
        val authResult = oAuthProvider.authenticate(
            provider = provider,
            accessToken = accessToken,
        )

        var isNewUser = false
        val socialUser = socialUserRepository.getSocialUser(
            provider = authResult.provider,
            identifier = authResult.identifier,
        ) ?: Unit.let {
            isNewUser = true
            createSocialUser(authResult)
        }

        val token = tokenProvider.generateToken(
            userId = socialUser.userId
        )

        return SocialLoginResult(
            token = token,
            isFirstLogin = isNewUser,
        )
    }

    fun refreshToken(
        refreshToken: String,
    ): SocialLoginResult {
        val userId = tokenProvider.getUserIdFromToken(refreshToken) ?: throw AuthTokenNotValidException()
        val token = tokenProvider.generateToken(userId)
        return SocialLoginResult(
            token = token,
            isFirstLogin = false,
        )
    }

    private fun createSocialUser(socialUser: SocialUser): SocialUser {
        val user = userRepository.saveUser(
            User(
                userId = identifierProvider.generate(),
                nickname = MockNickGenerator.generate(),
                profileImageUrl = null,
            )
        )

        return socialUserRepository.saveSocialUser(
            SocialUser(
                provider = socialUser.provider,
                identifier = socialUser.identifier,
                userId = user.userId,
            )
        )
    }
}
