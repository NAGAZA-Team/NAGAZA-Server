package kr.nagaza.nagazaserver.presenter.restapi.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "프로필 사진 변경 요청")
data class UpdateProfileImageUrlRequest(
    @Schema(description = "변경하고자 하는 이미지 주소", example = "")
    val profileImageUrl: String?,
)
