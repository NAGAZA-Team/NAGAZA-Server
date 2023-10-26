package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "카페 지역 필터 응답")
data class CafeAreaFilterResponse(
    @JsonProperty("address_1")
    @Schema(description = "첫번쨰 주소", example = "서울")
    val addressOne: String,

    @JsonProperty("address_2")
    @Schema(description = "두번쨰 주소", example = "강남")
    val addressTwo: String,

    @Schema(description = "해당 주소내의 카페 수", example = "23")
    val roomCount: Int,
)
