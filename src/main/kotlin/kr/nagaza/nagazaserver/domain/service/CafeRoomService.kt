package kr.nagaza.nagazaserver.domain.service

import jakarta.transaction.Transactional
import kr.nagaza.nagazaserver.domain.exception.CafeNotFoundException
import kr.nagaza.nagazaserver.domain.exception.CafeRoomNotFoundException
import kr.nagaza.nagazaserver.domain.model.CafeRoom
import kr.nagaza.nagazaserver.domain.repository.CafeRepository
import kr.nagaza.nagazaserver.domain.repository.CafeRoomRepository
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.RoomSearchQuery
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

    fun getRoomByRoomId(roomId: String): CafeRoom {
        return cafeRoomRepository.findByRoomId(roomId) ?: throw CafeRoomNotFoundException()
    }

    fun search(cafeRoomSearchQuery: RoomSearchQuery): List<CafeRoom> {
        return cafeRoomRepository.search(
            queryString = cafeRoomSearchQuery.queryString,
            genre = cafeRoomSearchQuery.genre,
            address1 = cafeRoomSearchQuery.address1,
            address2 = cafeRoomSearchQuery.address2,
            cafeId = cafeRoomSearchQuery.cafeId,
        )
    }
}
