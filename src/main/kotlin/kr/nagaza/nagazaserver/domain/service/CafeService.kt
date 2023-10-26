package kr.nagaza.nagazaserver.domain.service

import kr.nagaza.nagazaserver.domain.exception.CafeNotFoundException
import kr.nagaza.nagazaserver.domain.model.Cafe
import kr.nagaza.nagazaserver.domain.repository.CafeRepository
import org.springframework.stereotype.Service

@Service
class CafeService(
    private val cafeRepository: CafeRepository,
) {
    fun getAllCafes() = cafeRepository.findAll()
    fun getAllByAddresses(addressOne: String, addressTwo: String): List<Cafe> = cafeRepository.findAllByAddresses(addressOne, addressTwo)
    fun getById(cafeId: String): Cafe {
        return cafeRepository.findById(cafeId) ?: throw CafeNotFoundException()
    }
}
