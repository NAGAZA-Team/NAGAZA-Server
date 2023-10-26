package kr.nagaza.nagazaserver.domain.service

import kr.nagaza.nagazaserver.domain.model.AuthToken
import kr.nagaza.nagazaserver.domain.model.SocialProvider
import kr.nagaza.nagazaserver.domain.model.SocialUser
import kr.nagaza.nagazaserver.domain.model.User
import kr.nagaza.nagazaserver.domain.repository.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExtendWith(MockitoExtension::class)
class SocialLoginServiceTest {
    @Test
    fun `socialLogin() 테스트`() {
        // given
        val provider = SocialProvider.APPLE
        val accessToken = "asdf"
        val oAuthResult = SocialUser(
            provider = provider,
            identifier = "asdf",
            userId = "asdf",
        )
        val authToken = AuthToken(
            accessToken = "",
            refreshToken = "",
        )
        val oAuthProvider = mock<OAuthProvider> {
            on { authenticate(provider, accessToken) } doReturn oAuthResult
        }

        val socialUserRepository = mock<SocialUserRepository> {
            on { getSocialUser(any(), any()) } doReturn oAuthResult
        }

        val tokenProvider = mock<TokenProvider> {
            on { generateToken(any()) } doReturn authToken
        }

        val socialLoginService = SocialLoginService(
            tokenProvider = tokenProvider,
            oAuthProvider = oAuthProvider,
            socialUserRepository = socialUserRepository,
            identifierProvider = mock(),
            userRepository = mock(),
        )
        // when
        socialLoginService.socialLogin(
            provider = provider,
            accessToken = accessToken,
        )
        // then

    }

    @Test
    fun `socialLogin() 테스트 with 새로운 유저`() {
        // given
        val provider = SocialProvider.APPLE
        val accessToken = "asdf"
        val oAuthResult = SocialUser(
            provider = provider,
            identifier = "asdf",
            userId = "asdf",
        )
        val authToken = AuthToken(
            accessToken = "",
            refreshToken = "",
        )
        val oAuthProvider = mock<OAuthProvider> {
            on { authenticate(provider, accessToken) } doReturn oAuthResult
        }

        val socialUserRepository = mock<SocialUserRepository> {
            on { getSocialUser(any(), any()) } doReturn null
            on { saveSocialUser(any()) } doReturn oAuthResult
        }

        val tokenProvider = mock<TokenProvider> {
            on { generateToken(any()) } doReturn authToken
        }

        val userRepository = mock<UserRepository> {
            on { saveUser(any()) } doAnswer { it.arguments[0] as User }
        }

        val identifierProvider = mock<IdentifierProvider> {
            on { generate() } doReturn "asdf"
        }

        val socialLoginService = SocialLoginService(
            tokenProvider = tokenProvider,
            oAuthProvider = oAuthProvider,
            socialUserRepository = socialUserRepository,
            identifierProvider = identifierProvider,
            userRepository = userRepository,
        )
        // when
        socialLoginService.socialLogin(
            provider = provider,
            accessToken = accessToken,
        )
        // then

    }

    @Test
    fun `generateMockUserToken() 테스트`() {
        //given
        val authToken = AuthToken(
            accessToken = "",
            refreshToken = "",
        )
        val tokenProvider = mock<TokenProvider> {
            on { generateToken(any()) } doReturn authToken
        }

        val socialLoginService = SocialLoginService(
            tokenProvider = tokenProvider,
            oAuthProvider = mock(),
            socialUserRepository = mock(),
            identifierProvider = mock(),
            userRepository = mock(),
        )
        //when
        val generatedToken = socialLoginService.generateMockUserToken()

        //then
        assert(generatedToken.token == authToken)
    }

    @Test
    fun `refreshToken() 테스트`() {
        //given
        val authToken = AuthToken(
            accessToken = "",
            refreshToken = "",
        )
        val tokenProvider = mock<TokenProvider> {
            on { generateToken(any()) } doReturn authToken
            on { getUserIdFromToken(any()) } doReturn "userId"
        }

        val socialLoginService = SocialLoginService(
            tokenProvider = tokenProvider,
            oAuthProvider = mock(),
            socialUserRepository = mock(),
            identifierProvider = mock(),
            userRepository = mock(),
        )

        //when
        val generatedToken = socialLoginService.refreshToken("refreshToken")

        //then
        assert(generatedToken.token == authToken)
    }
}
