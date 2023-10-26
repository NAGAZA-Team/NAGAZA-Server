package kr.nagaza.nagazaserver.domain.repository

import kr.nagaza.nagazaserver.domain.model.Cafe

interface CafeRepository {
    fun findAll(): List<Cafe>
    fun findById(id: String): Cafe?
    fun saveCafe(cafe: Cafe): Cafe
}
