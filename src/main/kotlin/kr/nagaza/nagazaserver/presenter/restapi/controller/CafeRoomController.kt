package kr.nagaza.nagazaserver.presenter.restapi.controller

import kr.nagaza.nagazaserver.presenter.restapi.api.CafeRoomApi
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomResponse
import org.springframework.stereotype.Controller

@Controller
class CafeRoomController: CafeRoomApi {
    override fun getCafeRooms(cafeId: String): List<CafeRoomResponse> {
        TODO("Not yet implemented")
    }

    override fun getCafeRoom(cafeId: String, roomId: String): CafeRoomResponse {
        TODO("Not yet implemented")
    }
}
