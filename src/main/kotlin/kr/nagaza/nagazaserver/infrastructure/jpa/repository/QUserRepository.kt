package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.nagaza.nagazaserver.infrastructure.jpa.entity.QCafeRoomLikeEntity.Companion.cafeRoomLikeEntity
import kr.nagaza.nagazaserver.infrastructure.jpa.entity.QCafeRoomReviewEntity.Companion.cafeRoomReviewEntity
import kr.nagaza.nagazaserver.infrastructure.jpa.entity.QUserEntity.Companion.userEntity
import kr.nagaza.nagazaserver.infrastructure.jpa.projection.QUserSummaryProjection
import kr.nagaza.nagazaserver.infrastructure.jpa.projection.UserSummaryProjection
import org.springframework.stereotype.Repository

@Repository
class QUserRepository(
    private val jpaQueryFactory: JPAQueryFactory,
) {
    fun getUserSummary(userId: String): UserSummaryProjection? {
        return jpaQueryFactory
            .select(
                QUserSummaryProjection(
                    userId = userEntity.userId,
                    reviewCount = cafeRoomReviewEntity.count(),
                    likeCount = cafeRoomLikeEntity.count(),
                )
            )
            .from(userEntity)
            .leftJoin(cafeRoomReviewEntity).on(cafeRoomReviewEntity.userId.eq(userEntity.userId))
            .leftJoin(cafeRoomLikeEntity).on(cafeRoomLikeEntity.userId.eq(userEntity.userId))
            .where(userEntity.userId.eq(userId))
            .fetchOne()
    }
}
