package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import kr.nagaza.nagazaserver.domain.model.CafeRoom

@Schema(description = "카페 방 응답")
data class CafeRoomResponse(
    @Schema(description = "카페 방 아이디", example = "01HDNFJHCNS5E2W35YTB030TJ8")
    val roomId: String,
    @Schema(description = "카페 아이디", example = "01HDNFJHCNS5E2W35YTB030TJ8")
    val cafeId: String,
    @Schema(description = "제목", example = "형주의 방탈출")
    val title: String,
    @Schema(description = "설명", example = "형주의 방탈출 설명")
    val description: String,
    @Schema(description = "장르 (장르 Id)", example = "['1', '2']")
    val genre: Set<Int>,
    @Schema(description = "추천인원", example = "4")
    val recommendedUser: Int,
    @Schema(description = "방 이미지", example = "https://nagaza.kr/room/01HDNFJHCNS5E2W35YTB030TJ8.jpg")
    val roomImage: String,
    @Schema(description = "탈출 제한 시간", example = "60")
    val timeout: Int,
) {
    companion object {
        fun fromModel(model: CafeRoom): CafeRoomResponse {
            return CafeRoomResponse(
                roomId = model.roomId,
                cafeId = model.cafeId,
                title = model.title,
                description = model.description,
                genre = model.genre.map { it.genreId }.toSet(),
                recommendedUser = model.recommendedUserCnt,
                roomImage = model.roomImgUrl ?: "",
                timeout = model.timeout,
            )
        }
    }
}
