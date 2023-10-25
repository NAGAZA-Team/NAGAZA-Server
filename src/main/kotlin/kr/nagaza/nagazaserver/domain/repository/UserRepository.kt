package kr.nagaza.nagazaserver.domain.repository

import kr.nagaza.nagazaserver.domain.model.User

interface UserRepository {
    fun saveUser(user: User): User
    fun findById(userId: String): User?
}
