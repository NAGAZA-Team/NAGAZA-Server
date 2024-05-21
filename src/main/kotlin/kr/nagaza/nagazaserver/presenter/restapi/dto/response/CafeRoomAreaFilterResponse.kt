package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "방 지역 필터 응답")
data class CafeRoomAreaFilterItem(
    @JsonProperty("name")
    @Schema(description = "지역 구분 이름", example = "서울시")
    val name: String,
    @JsonProperty("depth")
    @Schema(description = "지역 구분 깊이 (0: 전체, 1: 시, 2: 구)", example = "1")
    val depth: Int,
    @JsonProperty("count")
    @Schema(description = "해당 지역에 속한 방 수", example = "23")
    val count: Int,
    @JsonProperty("children")
    @Schema(description = "하위 지역 목록", example = "[]")
    val children: List<CafeRoomAreaFilterItem>,
)
