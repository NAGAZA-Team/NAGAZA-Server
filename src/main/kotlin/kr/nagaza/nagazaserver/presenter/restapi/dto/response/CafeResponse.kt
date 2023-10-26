package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import kr.nagaza.nagazaserver.domain.model.Cafe

data class CafeResponse(
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
