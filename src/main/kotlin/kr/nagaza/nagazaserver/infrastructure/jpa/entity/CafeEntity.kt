package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

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
)
