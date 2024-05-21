package kr.nagaza.nagazaserver.presenter.restapi.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeAreaFilterResponse
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "카페 검색 API", description = "카페 검색 관련 API")
@RestController
@RequestMapping("/v1/cafes/rooms/search")
interface CafeSearchApi {
    @Operation(summary = "카페 검색", description = "문자열로 카페를 검색합니다.")
    @GetMapping(params = ["q"])
    fun searchCafe(
        @RequestParam("q") @Parameter(description = "검색어") query: String,
    ): List<CafeResponse>

    @Operation(summary = "지역 필터로 카페 조회", description = "지역 필터 내 모든 카페 목록을 조회합니다.")
    @GetMapping(params = ["address_1", "address_2"])
    fun searchCafeByAddress(
        @RequestParam("address_1") @Parameter(description = "첫번째 주소 키", example = "서울") addressOne: String,
        @RequestParam("address_2") @Parameter(description = "첫번째 주소 키", example = "강남") addressTwo: String,
    ): List<CafeResponse>

    @Operation(summary = "전체 카페 지역 필터", description = "전체 카페 지역 필터 목록을 반환합니다.")
    @GetMapping("/filter")
    fun getCafeAreaFilters(): List<CafeAreaFilterResponse>
}
