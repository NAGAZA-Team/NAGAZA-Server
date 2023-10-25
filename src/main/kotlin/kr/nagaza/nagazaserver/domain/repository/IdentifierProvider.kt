package kr.nagaza.nagazaserver.domain.repository

interface IdentifierProvider {
    fun generate(): String
}
