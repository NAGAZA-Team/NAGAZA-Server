package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import kr.nagaza.nagazaserver.domain.model.User
import kr.nagaza.nagazaserver.domain.repository.UserRepository
import kr.nagaza.nagazaserver.infrastructure.jpa.entity.UserEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val jpaUserRepository: JpaUserRepository,
) : UserRepository {
    override fun saveUser(user: User): User {
        return jpaUserRepository.save(
            UserEntity.fromModel(user)
        ).toModel()
    }

    override fun findById(userId: String): User? {
        return jpaUserRepository.findByIdOrNull(userId)?.toModel()
    }
}
