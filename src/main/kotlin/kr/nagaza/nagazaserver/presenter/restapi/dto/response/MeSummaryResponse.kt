package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import kr.nagaza.nagazaserver.domain.model.UserSummary

data class MeSummaryResponse(
    val gradeLevel: Int,
    val reviewCount: Int,
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
