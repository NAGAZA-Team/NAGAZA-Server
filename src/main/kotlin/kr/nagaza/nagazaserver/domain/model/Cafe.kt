package kr.nagaza.nagazaserver.domain.model

data class Cafe(
    val id: String,
    val franchiseId: String,
    val name: String,
    val description: String,
    val address: String?,
    val webUrl: String?,
    val phoneNumber: String?,
    val locationLat: Double?,
    val locationLng: Double?,
    val addressOne: String?,
    val addressTwo: String?,
)
