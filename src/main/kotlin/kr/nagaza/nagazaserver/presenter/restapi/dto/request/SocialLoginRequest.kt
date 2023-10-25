package kr.nagaza.nagazaserver.presenter.restapi.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(description = "소셜 로그인 요청")
data class SocialLoginRequest(
    @NotBlank
    @Schema(description = "소셜 로그인 제공자", example = "KAKAO|APPLE|NAVER")
    val provider: String,

    @NotBlank
    @Schema(description = "네이티브 로그인 후 얻은 엑세스 토큰", example = "1234567890")
    val accessToken: String,
)
