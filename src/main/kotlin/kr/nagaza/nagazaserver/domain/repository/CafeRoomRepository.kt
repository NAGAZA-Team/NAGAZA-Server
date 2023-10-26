package kr.nagaza.nagazaserver.domain.repository

import kr.nagaza.nagazaserver.domain.model.CafeRoom

interface CafeRoomRepository {
    fun getAllRoomByCafeId(cafeId: String): List<CafeRoom>
    fun findByRoomId(roomId: String): CafeRoom?
}
