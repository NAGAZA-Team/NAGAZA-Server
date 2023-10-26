package kr.nagaza.nagazaserver.presenter.restapi.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomReviewResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "카페 방 리뷰 API", description = "카페 방 리뷰 관련 API")
@RestController
@RequestMapping("/v1/cafes/{cafeId}/rooms/{roomId}/reviews")
interface CafeRoomReviewApi {
    @Operation(summary = "카페 방 전체 리뷰 목록", description = "카페 방의 전체 리뷰 목록을 반환합니다.")
    @GetMapping
    fun getReviews(
        @PathVariable cafeId: String,
        @PathVariable roomId: String,
    ): List<CafeRoomReviewResponse>
}
