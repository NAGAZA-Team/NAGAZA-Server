package kr.nagaza.nagazaserver.domain.repository

import kr.nagaza.nagazaserver.domain.model.SocialProvider
import kr.nagaza.nagazaserver.domain.model.SocialUser

interface OAuthProvider {
    fun authenticate(provider: SocialProvider, accessToken: String): SocialUser
}
