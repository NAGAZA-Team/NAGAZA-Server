package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import kr.nagaza.nagazaserver.domain.model.CafeRoom

data class CafeRoomResponse(
    val roomId: String,
) {
    companion object {
        fun fromModel(model: CafeRoom): CafeRoomResponse {
            return CafeRoomResponse(
                roomId = model.roomId,
            )
        }
    }
}
