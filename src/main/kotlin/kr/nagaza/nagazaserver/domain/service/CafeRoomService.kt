package kr.nagaza.nagazaserver.domain.service

import jakarta.transaction.Transactional
import kr.nagaza.nagazaserver.domain.exception.CafeNotFoundException
import kr.nagaza.nagazaserver.domain.model.CafeRoom
import kr.nagaza.nagazaserver.domain.repository.CafeRepository
import kr.nagaza.nagazaserver.domain.repository.CafeRoomRepository
import org.springframework.stereotype.Service

@Service
class CafeRoomService(
    private val cafeRepository: CafeRepository,
    private val cafeRoomRepository: CafeRoomRepository,
) {
    @Transactional
    fun getRoomsByCafeId(cafeId: String): List<CafeRoom> {
        val cafe = cafeRepository.findById(cafeId) ?: throw CafeNotFoundException()
        return cafeRoomRepository.getAllRoomByCafeId(
            cafeId = cafe.id,
        )
    }
}
