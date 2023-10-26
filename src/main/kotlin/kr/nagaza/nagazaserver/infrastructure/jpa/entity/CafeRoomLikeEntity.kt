package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "cafe_room_like")
class CafeRoomLikeEntity(
    @Id
    @Column(name = "room_id")
    val roomId: String,

    @Id
    @Column(name = "user_id")
    val userId: String,
)
