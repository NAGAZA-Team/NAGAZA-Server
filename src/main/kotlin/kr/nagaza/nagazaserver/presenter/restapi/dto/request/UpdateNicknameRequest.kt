package kr.nagaza.nagazaserver.presenter.restapi.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(description = "닉네임 변경 요청")
data class UpdateNicknameRequest(
    @NotBlank
    @Schema(description = "변경하고자 하는 닉네임", example = "CChuYong")
    val nickName: String,
)
