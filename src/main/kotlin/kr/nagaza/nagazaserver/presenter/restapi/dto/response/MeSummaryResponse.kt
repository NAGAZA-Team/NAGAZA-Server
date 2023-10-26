package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import kr.nagaza.nagazaserver.domain.model.UserSummary

@Schema(description = "내 정보 통계 응답")
data class MeSummaryResponse(
    @Schema(description = "등급", example = "1")
    val gradeLevel: Int,

    @Schema(description = "리뷰 수", example = "1")
    val reviewCount: Int,

    @Schema(description = "좋아요 수", example = "1")
    val likeCount: Int,
) {
    companion object {
        fun fromModel(model: UserSummary) = MeSummaryResponse(
            gradeLevel = model.gradeLevel,
            reviewCount = model.reviewCount,
            likeCount = model.likeCount,
        )
    }
}
