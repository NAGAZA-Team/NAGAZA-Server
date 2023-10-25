package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import kr.nagaza.nagazaserver.domain.model.SocialProvider
import kr.nagaza.nagazaserver.domain.model.SocialUser
import kr.nagaza.nagazaserver.domain.repository.SocialUserRepository
import kr.nagaza.nagazaserver.infrastructure.jpa.entity.SocialUserEntity
import kr.nagaza.nagazaserver.infrastructure.jpa.entity.SocialUserEntityKey
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class SocialUserRepositoryImpl(
    private val jpaSocialUserRepository: JpaSocialUserRepository,
): SocialUserRepository {
    override fun getSocialUser(provider: SocialProvider, identifier: String): SocialUser? {
        return jpaSocialUserRepository.findByIdOrNull(
            SocialUserEntityKey(
                socialProvider = provider,
                socialIdentifier = identifier,
            )
        )?.toModel()
    }

    override fun saveSocialUser(socialUser: SocialUser): SocialUser {
        return jpaSocialUserRepository.save(
            SocialUserEntity.fromModel(socialUser)
        ).toModel()
    }
}
