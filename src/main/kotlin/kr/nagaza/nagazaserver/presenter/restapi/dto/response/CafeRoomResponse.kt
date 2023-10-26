package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import kr.nagaza.nagazaserver.domain.model.CafeRoom

@Schema(description = "카페 방 응답")
data class CafeRoomResponse(
    @Schema(description = "카페 방 아이디", example = "01HDNFJHCNS5E2W35YTB030TJ8")
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
