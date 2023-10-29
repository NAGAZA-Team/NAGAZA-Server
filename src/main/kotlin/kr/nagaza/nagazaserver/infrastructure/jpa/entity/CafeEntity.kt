package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import kr.nagaza.nagazaserver.domain.model.Cafe

@Entity(name = "cafe")
class CafeEntity(
    @Id
    @Column(name = "cafe_id")
    val cafeId: String,

    @Column(name = "franchise_id")
    val franchiseId: String,

    @Column(name = "cafe_name")
    val cafeName: String,

    @Column(name = "description")
    val description: String,

    @Column(name = "address")
    val address: String?,

    @Column(name = "web_url")
    val webUrl: String?,

    @Column(name = "phone_number")
    val phoneNumber: String?,

    @Column(name = "location_lat")
    val locationLat: Double?,

    @Column(name = "location_lng")
    val locationLng: Double?,

    @Column(name = "addr_1")
    val addressOne: String?,

    @Column(name = "addr_2")
    val addressTwo: String?,
) {
    fun toModel() = Cafe(
        id = cafeId,
        franchiseId = franchiseId,
        name = cafeName,
        description = description,
        address = address,
        webUrl = webUrl,
        phoneNumber = phoneNumber,
        locationLat = locationLat,
        locationLng = locationLng,
        addressOne = addressOne,
        addressTwo = addressTwo,
    )

    companion object {
        fun fromModel(cafe: Cafe) = CafeEntity(
            cafeId = cafe.id,
            franchiseId = cafe.franchiseId,
            cafeName = cafe.name,
            description = cafe.description,
            address = cafe.address,
            webUrl = cafe.webUrl,
            phoneNumber = cafe.phoneNumber,
            locationLat = cafe.locationLat,
            locationLng = cafe.locationLng,
            addressOne = cafe.addressOne,
            addressTwo = cafe.addressTwo,
        )
    }
}
