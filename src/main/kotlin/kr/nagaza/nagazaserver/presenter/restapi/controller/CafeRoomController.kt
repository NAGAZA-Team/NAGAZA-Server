package kr.nagaza.nagazaserver.presenter.restapi.controller

import kr.nagaza.nagazaserver.domain.service.CafeRoomService
import kr.nagaza.nagazaserver.presenter.restapi.api.CafeRoomApi
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.RoomSearchQuery
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomResponse
import org.springframework.stereotype.Controller

@Controller
class CafeRoomController(
    private val cafeRoomService: CafeRoomService,
) : CafeRoomApi {
    override fun getCafeRoom(roomId: String): CafeRoomResponse {
        return cafeRoomService.getRoomByRoomId(roomId)
            .let(CafeRoomResponse::fromModel)
    }

    override fun searchRooms(
        queryString: String?,
        genre: String?,
        address1: String?,
        address2: String?,
        cafeId: String?,
    ): List<CafeRoomResponse> {
        return cafeRoomService.search(
            RoomSearchQuery(
                queryString = queryString,
                genre = genre,
                address1 = address1,
                address2 = address2,
                cafeId = cafeId,
            ),
        ).map(CafeRoomResponse::fromModel)
    }
}
