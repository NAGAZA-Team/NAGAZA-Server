package kr.nagaza.nagazaserver.presenter.restapi.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import kr.nagaza.nagazaserver.presenter.restapi.advice.RequestUser
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.UpdateNicknameRequest
import kr.nagaza.nagazaserver.presenter.restapi.dto.request.UpdateProfileImageUrlRequest
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.CafeRoomReviewResponse
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.MeResponse
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.MeSummaryResponse
import org.springframework.web.bind.annotation.*

@Tag(name = "내 정보 API", description = "내 정보 관련 API")
@RestController
@RequestMapping("/v1/me")
interface MeApi {
    @Operation(summary = "내 정보 조회", description = "내 정보를 조회합니다.")
    @GetMapping
    fun getMe(
        @RequestUser userId: String,
    ): MeResponse

    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "내가 작성한 리뷰 목록을 조회합니다.")
    @GetMapping("/reviews")
    fun getMyReviews(
        @RequestUser userId: String,
    ): List<CafeRoomReviewResponse>

    @Operation(summary = "닉네임 변경", description = "내 닉네임을 변경합니다.")
    @PatchMapping("/nickname")
    fun updateNickname(
        @RequestUser userId: String,
        @RequestBody @Valid request: UpdateNicknameRequest,
    ): MeResponse

    @Operation(summary = "프로필 이미지 변경", description = "내 프로필 이미지를 변경합니다.")
    @PatchMapping("/profile-image")
    fun updateProfileImage(
        @RequestUser userId: String,
        @RequestBody @Valid request: UpdateProfileImageUrlRequest,
    ): MeResponse

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴합니다.")
    @PostMapping("/quit")
    fun quitNagaza(
        @RequestUser userId: String,
    )

    @Operation(summary = "내 통계 정보 확인", description = "나의 통계 정보(리뷰 수, 찜 수 등)을 확인합니다.")
    @GetMapping("/summary")
    fun getMySummary(
        @RequestUser userId: String,
    ): MeSummaryResponse
}
