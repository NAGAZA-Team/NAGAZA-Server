package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.*
import kr.nagaza.nagazaserver.domain.model.CafeRoom

@Entity(name = "cafe_room")
class CafeRoomEntity(
    @Id
    @Column(name = "room_id")
    val roomId: String,

    @Column(name = "cafe_id")
    val cafeId: String,

    @Column(name = "genre")
    val genre: String,

    @Column(name = "timeout")
    val timeout: Int,

    @Column(name = "recommend_user")
    val recommendUserCnt: Int,

    @Column(name = "room_img_url")
    val roomImgUrl: String?,

    @Column(name = "description")
    val description: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "cafe_room_like",
        joinColumns = [JoinColumn(name = "room_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    val likedUsers: MutableSet<UserEntity> = mutableSetOf(),

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    val reviews: MutableList<CafeRoomReviewEntity> = mutableListOf(),
) {
    fun toModel() = CafeRoom(
        roomId = roomId,
        cafeId = cafeId,
        genre = genre,
        timeout = timeout,
        recommendedUserCnt = recommendUserCnt,
        roomImgUrl = roomImgUrl,
        description = description,
    )

    companion object {
        fun fromModel(model: CafeRoom) = CafeRoomEntity(
            roomId = model.roomId,
            cafeId = model.cafeId,
            genre = model.genre,
            timeout = model.timeout,
            recommendUserCnt = model.recommendedUserCnt,
            roomImgUrl = model.roomImgUrl,
            description = model.description,
        )
    }
}
