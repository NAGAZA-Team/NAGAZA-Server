package kr.nagaza.nagazaserver.presenter.restapi.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "토큰 갱신 요청")
data class RefreshTokenRequest(
    @Schema(description = "갱신 대상 토큰", example = "")
    val refreshToken: String,
)
