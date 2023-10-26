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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CafeRoomLikeEntity) return false

        if (roomId != other.roomId) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = roomId.hashCode()
        result = 31 * result + userId.hashCode()
        return result
    }
}
