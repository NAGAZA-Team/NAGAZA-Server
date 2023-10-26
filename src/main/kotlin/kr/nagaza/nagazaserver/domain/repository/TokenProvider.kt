package kr.nagaza.nagazaserver.domain.repository

import kr.nagaza.nagazaserver.domain.model.AuthToken

interface TokenProvider {
    fun generateToken(userId: String): AuthToken
    fun isTokenValid(token: String): Boolean
    fun getUserIdFromToken(token: String): String?
}
