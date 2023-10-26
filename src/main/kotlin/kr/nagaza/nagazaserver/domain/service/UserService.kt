package kr.nagaza.nagazaserver.domain.service

import jakarta.transaction.Transactional
import kr.nagaza.nagazaserver.domain.exception.DomainException
import kr.nagaza.nagazaserver.domain.model.User
import kr.nagaza.nagazaserver.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    @Transactional
    fun getMeInfo(userId: String): User {
        return userRepository.findById(userId) ?: throw DomainException()
    }

    @Transactional
    fun updateNickName(userId: String, nickname: String): User {
        val user = userRepository.findById(userId) ?: throw DomainException()
        val newUser = user.copy(
            nickname = nickname,
        )
        return userRepository.saveUser(newUser)
    }

    @Transactional
    fun updateProfileImageUrl(userId: String, profileImageUrl: String?): User {
        val user = userRepository.findById(userId) ?: throw DomainException()
        val newUser = user.copy(
            profileImageUrl = profileImageUrl,
        )
        return userRepository.saveUser(newUser)
    }
}
