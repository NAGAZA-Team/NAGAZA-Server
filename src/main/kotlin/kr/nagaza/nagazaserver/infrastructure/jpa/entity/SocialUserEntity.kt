package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.*
import kr.nagaza.nagazaserver.domain.model.SocialProvider
import kr.nagaza.nagazaserver.domain.model.SocialUser
import java.io.Serializable

@IdClass(SocialUserEntityKey::class)
@Entity(name = "social_user")
class SocialUserEntity(
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "social_provider")
    val socialProvider: SocialProvider,

    @Id
    @Column(name = "social_identifier")
    val socialIdentifier: String,

    @Column(name = "user_id")
    val userId: String,
) {
    fun toModel() = SocialUser(
        provider = socialProvider,
        identifier = socialIdentifier,
        userId = userId,
    )

    companion object {
        fun fromModel(socialUser: SocialUser) = SocialUserEntity(
            socialProvider = socialUser.provider,
            socialIdentifier = socialUser.identifier,
            userId = socialUser.userId,
        )
    }
}

class SocialUserEntityKey(
    val socialProvider: SocialProvider = SocialProvider.APPLE,
    val socialIdentifier: String = "",
) : Serializable
