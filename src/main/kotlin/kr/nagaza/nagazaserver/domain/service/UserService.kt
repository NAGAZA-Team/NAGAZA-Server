package kr.nagaza.nagazaserver.domain.service

import jakarta.transaction.Transactional
import kr.nagaza.nagazaserver.domain.exception.UserNotFoundException
import kr.nagaza.nagazaserver.domain.model.User
import kr.nagaza.nagazaserver.domain.repository.SocialUserRepository
import kr.nagaza.nagazaserver.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val socialUserRepository: SocialUserRepository,
) {
    @Transactional
    fun getMeInfo(userId: String): User {
        return userRepository.findById(userId) ?: throw UserNotFoundException()
    }

    @Transactional
    fun updateNickName(userId: String, nickname: String): User {
        val user = userRepository.findById(userId) ?: throw UserNotFoundException()
        val newUser = user.copy(
            nickname = nickname,
        )
        return userRepository.saveUser(newUser)
    }

    @Transactional
    fun updateProfileImageUrl(userId: String, profileImageUrl: String?): User {
        val user = userRepository.findById(userId) ?: throw UserNotFoundException()
        val newUser = user.copy(
            profileImageUrl = profileImageUrl,
        )
        return userRepository.saveUser(newUser)
    }

    @Transactional
    fun quitNagaza(userId: String) {
        val user = userRepository.findById(userId) ?: throw UserNotFoundException()
        val socialUser = socialUserRepository.findAllByUserId(
            userId = userId
        )
        socialUser.forEach {
            socialUserRepository.deleteUser(it)
        }


        val newUser = user.copy(
            nickname = "탈퇴한 유저",
            profileImageUrl = null,
        )
        userRepository.saveUser(newUser)
    }
}
