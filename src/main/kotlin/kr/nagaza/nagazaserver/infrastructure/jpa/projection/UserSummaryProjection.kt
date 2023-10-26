package kr.nagaza.nagazaserver.infrastructure.jpa.projection

import com.querydsl.core.annotations.QueryProjection

data class UserSummaryProjection @QueryProjection constructor(
    val userId: String,
    val reviewCount: Long,
    val likeCount: Long,
)
