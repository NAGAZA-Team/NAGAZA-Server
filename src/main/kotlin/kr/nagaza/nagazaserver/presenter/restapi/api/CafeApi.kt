package kr.nagaza.nagazaserver.presenter.restapi.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "카페 API", description = "카페 관련 API")
@RestController
@RequestMapping("/v1/cafes")
interface CafeApi {
    @Operation(summary = "카페 전체 목록", description = "전체 카페 목록을 반환합니다.")
    @GetMapping
    fun getCafes(): List<CafeResponse>

    @Operation(summary = "특정 카페 조회", description = "특정 카페 정보를 조회합니다.")
    @GetMapping("/{cafeId}")
    fun getCafeByCafeId(
        @PathVariable cafeId: String,
    ): CafeResponse
}
