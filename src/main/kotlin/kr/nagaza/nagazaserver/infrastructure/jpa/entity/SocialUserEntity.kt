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
    @Column(name = "user_id", nullable = false)
    val userId: String,
) {
    fun toModel() =
        SocialUser(
            provider = socialProvider,
            identifier = socialIdentifier,
            userId = userId,
        )

    companion object {
        fun fromModel(socialUser: SocialUser) =
            SocialUserEntity(
                socialProvider = socialUser.provider,
                socialIdentifier = socialUser.identifier,
                userId = socialUser.userId,
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SocialUserEntity) return false

        if (socialProvider != other.socialProvider) return false
        if (socialIdentifier != other.socialIdentifier) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = socialProvider.hashCode()
        result = 31 * result + socialIdentifier.hashCode()
        result = 31 * result + userId.hashCode()
        return result
    }
}

data class SocialUserEntityKey(
    val socialProvider: SocialProvider = SocialProvider.APPLE,
    val socialIdentifier: String = "",
) : Serializable
