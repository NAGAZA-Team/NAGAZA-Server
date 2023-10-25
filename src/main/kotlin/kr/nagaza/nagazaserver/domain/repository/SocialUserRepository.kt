package kr.nagaza.nagazaserver.domain.repository

import kr.nagaza.nagazaserver.domain.model.SocialProvider
import kr.nagaza.nagazaserver.domain.model.SocialUser

interface SocialUserRepository {
    fun getSocialUser(provider: SocialProvider, identifier: String): SocialUser?
    fun saveSocialUser(socialUser: SocialUser): SocialUser
}
