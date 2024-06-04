package kr.nagaza.nagazaserver.presenter.restapi.dto.request

class RoomSearchQuery(
    val queryString: String?,
    val address1: String?,
    val address2: String?,
    val genre: Int?,
    val cafeId: String?,
)
