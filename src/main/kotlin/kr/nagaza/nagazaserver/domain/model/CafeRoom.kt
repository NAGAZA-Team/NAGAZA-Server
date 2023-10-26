package kr.nagaza.nagazaserver.domain.model

data class CafeRoom(
    val roomId: String,
    val cafeId: String,
    val genre: String,
    val timeout: Int,
    val recommendedUserCnt: Int,
    val roomImgUrl: String?,
    val description: String,
)
