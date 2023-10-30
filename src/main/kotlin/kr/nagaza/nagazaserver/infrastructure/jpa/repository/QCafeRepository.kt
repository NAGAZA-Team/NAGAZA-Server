package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import kr.nagaza.nagazaserver.infrastructure.jpa.entity.CafeEntity
import kr.nagaza.nagazaserver.infrastructure.jpa.entity.QCafeEntity.Companion.cafeEntity
import org.springframework.stereotype.Repository

@Repository
class QCafeRepository(
    private val jpaQueryFactory: JPAQueryFactory,
) {
    fun searchCafeByQuery(query: String): List<CafeEntity> {
        return jpaQueryFactory
            .select(cafeEntity)
            .where(cafeEntity.cafeName.like("%${query}%"))
            .fetch()
    }
}
