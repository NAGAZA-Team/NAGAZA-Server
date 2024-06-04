package kr.nagaza.nagazaserver.presenter.restapi.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomAreaFilterItem
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomResponse
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "카페 방 API", description = "카페 방 관련 API")
@RestController
@RequestMapping("/v1/rooms")
interface CafeRoomApi {
    @Operation(summary = "카페 전체 방 목록", description = "카페 내의 전체 방 목록을 반환합니다.")
    @GetMapping("/search")
    fun searchRooms(
        @Param("queryString") queryString: String?,
        @Param("genre") genre: Int?,
        @Param("address1") address1: String?,
        @Param("address2") address2: String?,
        @Param("cafeId") cafeId: String?,
    ): List<CafeRoomResponse>

    @Operation(summary = "특정 방 조회", description = "카페 내의 특정 방 정보를 조회합니다.")
    @GetMapping("/{roomId}")
    fun getCafeRoom(
        @PathVariable roomId: String,
    ): CafeRoomResponse

    @Operation(summary = "카페 방 지역 필터", description = "카페 방 지역 필터 목록을 반환합니다.")
    @GetMapping("/filter")
    fun getCafeRoomAreaFilters(): CafeRoomAreaFilterItem
}
