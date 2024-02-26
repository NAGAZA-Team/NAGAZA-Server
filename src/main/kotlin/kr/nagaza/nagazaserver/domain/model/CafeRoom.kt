package kr.nagaza.nagazaserver.domain.model

import java.util.*

data class CafeRoom(
    val roomId: String,
    val cafeId: String,
    val title: String,
    val description: String,
    val genre: Set<Genre>,
    val timeout: Int,
    val recommendedUserCnt: Int,
    val roomImgUrl: String?,
    val createdAt: Date,
    val updatedAt: Date,
)
