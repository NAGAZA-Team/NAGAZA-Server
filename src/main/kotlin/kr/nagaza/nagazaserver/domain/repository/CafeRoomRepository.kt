package kr.nagaza.nagazaserver.domain.repository

import kr.nagaza.nagazaserver.domain.model.CafeRoom

interface CafeRoomRepository {
    fun getAllRoomByCafeId(cafeId: String): List<CafeRoom>

    fun findByRoomId(roomId: String): CafeRoom?

    fun search(
        queryString: String?,
        genre: String?,
        address1: String?,
        address2: String?,
        cafeId: String?,
    ): List<CafeRoom>
}
