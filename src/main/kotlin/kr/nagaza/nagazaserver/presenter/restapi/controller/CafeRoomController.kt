package kr.nagaza.nagazaserver.presenter.restapi.controller

import kr.nagaza.nagazaserver.domain.service.CafeRoomService
import kr.nagaza.nagazaserver.presenter.restapi.api.CafeRoomApi
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomResponse
import org.springframework.stereotype.Controller

@Controller
class CafeRoomController(
    private val cafeRoomService: CafeRoomService,
) : CafeRoomApi {
    override fun getCafeRooms(cafeId: String): List<CafeRoomResponse> {
        return cafeRoomService.getRoomsByCafeId(cafeId)
            .map { CafeRoomResponse.fromModel(it) }
    }

    override fun getCafeRoom(cafeId: String, roomId: String): CafeRoomResponse {
        return CafeRoomResponse.fromModel(
            cafeRoomService.getRoomByRoomId(roomId)
        )
    }
}
