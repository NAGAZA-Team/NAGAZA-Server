package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import kr.nagaza.nagazaserver.domain.model.User

data class MeResponse(
    val nickname: String,
) {
    companion object {
        fun fromModel(model: User) = MeResponse(
            nickname = model.nickname,
        )
    }
}
