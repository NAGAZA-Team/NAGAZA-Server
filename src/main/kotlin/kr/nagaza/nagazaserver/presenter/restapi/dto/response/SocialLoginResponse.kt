package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "로그인 결과 토큰 응답")
data class SocialLoginResponse(
    @Schema(description = "엑세스 토큰")
    val accessToken: String,

    @Schema(description = "리프레시 토큰")
    val refreshToken: String,

    @Schema(description = "첫 로그인 여부 (true면 회원가입)")
    val isFirstLogin: Boolean,
)
