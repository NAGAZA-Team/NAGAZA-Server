package kr.nagaza.nagazaserver.domain.repository

import kr.nagaza.nagazaserver.domain.model.Cafe

interface CafeRepository {
    fun findById(id: String): Cafe?
    fun saveCafe(cafe: Cafe): Cafe
}
