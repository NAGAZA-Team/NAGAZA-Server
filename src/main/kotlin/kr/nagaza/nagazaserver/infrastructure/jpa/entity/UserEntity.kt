package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import kr.nagaza.nagazaserver.domain.model.User

@Entity(name = "user")
class UserEntity(
    @Id
    @Column(name = "user_id")
    val userId: String,

    @Column(name = "nickname")
    val nickname: String,

    @Column(name = "profile_image_url")
    val profileImageUrl: String?,
) {
    fun toModel() = User(
        userId = userId,
        nickname = nickname,
        profileImageUrl = profileImageUrl,
    )

    companion object {
        fun fromModel(model: User) = UserEntity(
            userId = model.userId,
            nickname = model.nickname,
            profileImageUrl = model.profileImageUrl,
        )
    }
}
