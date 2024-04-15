package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import kr.nagaza.nagazaserver.domain.model.Cafe

@Schema(description = "카페 응답")
data class CafeResponse(
    @Schema(description = "카페 아이디", example = "01HDNFJHCNS5E2W35YTB030TJ8")
    val cafeId: String,
    @Schema(description = "프랜차이즈 아이디", example = "01HDNFJHCNS5E2W35YTB030TJ8")
    val franchiseId: String,
    @Schema(description = "카페 이름", example = "카페카페")
    val cafeName: String,
    @Schema(description = "카페 설명", example = "재밋는 방탈출")
    val description: String,
    @Schema(description = "주소", example = "재밋는 방탈출")
    val address: String?,
    @Schema(description = "사이트", example = "https://www.naver.com")
    val webUrl: String?,
    @Schema(description = "전화번호", example = "010-1234-5678")
    val phoneNumber: String?,
    @Schema(description = "위도", example = "37.123456")
    val latitude: Double?,
    @Schema(description = "경도", example = "37.123456")
    val longitude: Double?,
    @Schema(description = "주소 1", example = "서울시")
    val addr1: String?,
    @Schema(description = "주소 2", example = "중랑구")
    val addr2: String?,
) {
    companion object {
        fun fromModel(model: Cafe): CafeResponse {
            return CafeResponse(
                cafeId = model.id,
                franchiseId = model.franchiseId,
                cafeName = model.name,
                description = model.description,
                address = model.address,
                webUrl = model.webUrl,
                phoneNumber = model.phoneNumber,
                latitude = model.locationLat,
                longitude = model.locationLng,
                addr1 = model.addressOne,
                addr2 = model.addressTwo,
            )
        }
    }
}
