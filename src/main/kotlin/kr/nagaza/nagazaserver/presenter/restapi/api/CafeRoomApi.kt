package kr.nagaza.nagazaserver.presenter.restapi.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "카페 방 API", description = "카페 방 관련 API")
@RestController
@RequestMapping("/v1/cafes/{cafeId}/rooms")
interface CafeRoomApi {
    @Operation(summary = "카페 전체 방 목록", description = "카페 내의 전체 방 목록을 반환합니다.")
    @GetMapping
    fun getCafeRooms(
        @PathVariable cafeId: String,
    ): List<CafeRoomResponse>

    @Operation(summary = "특정 방 조회", description = "카페 내의 특정 방 정보를 조회합니다.")
    @GetMapping("/{roomId}")
    fun getCafeRoom(
        @PathVariable cafeId: String,
        @PathVariable roomId: String,
    ): CafeRoomResponse
}
