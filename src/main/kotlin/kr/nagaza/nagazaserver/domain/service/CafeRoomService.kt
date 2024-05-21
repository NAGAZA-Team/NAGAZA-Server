package kr.nagaza.nagazaserver.domain.service

import jakarta.transaction.Transactional
import kr.nagaza.nagazaserver.domain.exception.CafeNotFoundException
import kr.nagaza.nagazaserver.domain.exception.CafeRoomNotFoundException
import kr.nagaza.nagazaserver.domain.model.CafeRoom
import kr.nagaza.nagazaserver.domain.repository.CafeRepository
import kr.nagaza.nagazaserver.domain.repository.CafeRoomRepository
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.RoomSearchQuery
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomAreaFilterItem
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

    fun getRoomAreaFilters(): CafeRoomAreaFilterItem {
        val res = cafeRoomRepository.getRoomCountGroupByAddress()

        val depth1 =
            res.groupBy { it.address1 }.map {
                CafeRoomAreaFilterItem(
                    name = it.key,
                    depth = 1,
                    count = it.value.size,
                    children =
                        it.value.map { depth2 ->
                            CafeRoomAreaFilterItem(
                                name = depth2.address2,
                                depth = 2,
                                count = depth2.count,
                                children = emptyList(),
                            )
                        },
                )
            }

        return CafeRoomAreaFilterItem(
            name = "전국",
            depth = 0,
            count = res.size,
            children = depth1,
        )
    }
}
