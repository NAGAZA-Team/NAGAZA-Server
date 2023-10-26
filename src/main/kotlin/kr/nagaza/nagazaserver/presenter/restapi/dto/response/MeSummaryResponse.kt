package kr.nagaza.nagazaserver.presenter.restapi.dto.response

data class MeSummaryResponse(
    val gradeLevel: Int,
    val reviewCount: Int,
    val likeCount: Int,
)
