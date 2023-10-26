package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import kr.nagaza.nagazaserver.domain.model.Cafe

@Schema(description = "카페 응답")
data class CafeResponse(
    @Schema(description = "카페 아이디", example = "01HDNFJHCNS5E2W35YTB030TJ8")
    val cafeId: String,
) {
    companion object {
        fun fromModel(model: Cafe): CafeResponse {
            return CafeResponse(
                cafeId = model.id,
            )
        }
    }
}
