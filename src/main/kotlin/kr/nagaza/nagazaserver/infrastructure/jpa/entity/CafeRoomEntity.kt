package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.*
import kr.nagaza.nagazaserver.domain.model.CafeRoom
import org.hibernate.annotations.CreationTimestamp
import java.util.*

@Entity(name = "cafe_room")
class CafeRoomEntity(
    @Id
    @Column(name = "room_id")
    val roomId: String,
    @Column(name = "cafe_id")
    val cafeId: String,
    @Column(name = "title")
    val title: String,
    @Column(name = "description")
    val description: String,
    @ManyToMany
    @JoinTable(
        name = "cafe_room_genre",
        joinColumns = [JoinColumn(name = "room_id")],
        inverseJoinColumns = [JoinColumn(name = "genre_id")],
    )
    val genre: MutableSet<GenreEntity> = mutableSetOf(),
    @Column(name = "timeout")
    val timeout: Int,
    @Column(name = "recommend_user")
    val recommendUserCnt: Int,
    @Column(name = "room_img_url")
    val roomImgUrl: String?,
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "cafe_room_like",
        joinColumns = [JoinColumn(name = "room_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")],
    )
    val likedUsers: MutableSet<UserEntity> = mutableSetOf(),
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    val reviews: MutableList<CafeRoomReviewEntity> = mutableListOf(),
    @Column(name = "created_at")
    @CreationTimestamp()
    val createdAt: Date,
    val updatedAt: Date,
) {
    fun toModel() =
        CafeRoom(
            roomId = roomId,
            cafeId = cafeId,
            genre = genre.map { it.toModel() }.toSet(),
            timeout = timeout,
            recommendedUserCnt = recommendUserCnt,
            roomImgUrl = roomImgUrl,
            description = description,
            title = title,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )

    companion object {
        fun fromModel(model: CafeRoom) =
            CafeRoomEntity(
                roomId = model.roomId,
                cafeId = model.cafeId,
                genre = model.genre.map { GenreEntity.fromModel(it) }.toMutableSet(),
                timeout = model.timeout,
                recommendUserCnt = model.recommendedUserCnt,
                roomImgUrl = model.roomImgUrl,
                title = model.title,
                description = model.description,
                createdAt = model.createdAt,
                updatedAt = model.updatedAt,
            )
    }
}
