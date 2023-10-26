package kr.nagaza.nagazaserver.domain.service

import jakarta.transaction.Transactional
import kr.nagaza.nagazaserver.domain.exception.UserNotFoundException
import kr.nagaza.nagazaserver.domain.model.User
import kr.nagaza.nagazaserver.domain.model.UserSummary
import kr.nagaza.nagazaserver.domain.repository.SocialUserRepository
import kr.nagaza.nagazaserver.domain.repository.UserRepository
import kr.nagaza.nagazaserver.infrastructure.jpa.repository.QUserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val socialUserRepository: SocialUserRepository,
    private val qUserRepository: QUserRepository,
) {
    @Transactional
    fun getUserInfo(userId: String): User {
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

    fun getUserSummary(userId: String): UserSummary {
        val result = qUserRepository.getUserSummary(userId) ?: throw UserNotFoundException()
        return UserSummary(
            reviewCount = result.reviewCount.toInt(),
            likeCount = result.likeCount.toInt(),
            gradeLevel = evaluateGradeLevel(result.reviewCount.toInt())
        )
    }

    private fun evaluateGradeLevel(reviewCount: Int) = when (reviewCount) {
        in 0..9 -> 1
        in 10..29 -> 2
        in 30..49 -> 3
        in 50..99 -> 4
        else -> 5
    }
}
