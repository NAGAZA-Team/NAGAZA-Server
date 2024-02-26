package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import com.querydsl.jpa.impl.JPAQuery
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import kr.nagaza.nagazaserver.domain.model.CafeRoom
import kr.nagaza.nagazaserver.domain.repository.CafeRoomRepository
import kr.nagaza.nagazaserver.infrastructure.jpa.entity.QCafeEntity
import kr.nagaza.nagazaserver.infrastructure.jpa.entity.QCafeRoomEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class CafeRoomRepositoryImpl(
    private val jpaCafeRoomRepository: JpaCafeRoomRepository,
    @PersistenceContext
    private val entityManager: EntityManager,
) : CafeRoomRepository {
    override fun getAllRoomByCafeId(cafeId: String): List<CafeRoom> {
        return jpaCafeRoomRepository.findAllByCafeId(cafeId)
            .map {
                it.toModel()
            }
    }

    override fun findByRoomId(roomId: String): CafeRoom? {
        return jpaCafeRoomRepository.findByIdOrNull(roomId)?.toModel()
    }

    override fun search(
        queryString: String?,
        genre: String?,
        address1: String?,
        address2: String?,
        cafeId: String?,
    ): List<CafeRoom> {
        val cafeRoomEntity = QCafeRoomEntity.cafeRoomEntity
        val cafeEntity = QCafeEntity.cafeEntity
        val query =
            JPAQuery<CafeRoom>(entityManager)
                .select(cafeRoomEntity)
                .from(cafeRoomEntity)
                .leftJoin(cafeEntity).on(cafeRoomEntity.cafeId.eq(cafeEntity.cafeId))
                .leftJoin(cafeRoomEntity.genre)

        if (queryString != null) {
            query.where(cafeRoomEntity.title.contains(queryString))
        }

        if (genre != null) {
            query.where(cafeRoomEntity.genre.any().genreId.eq(genre))
        }

        if (address1 != null) {
            query.where(cafeEntity.addressOne.eq(address1))
        }

        if (address2 != null) {
            query.where(cafeEntity.addressTwo.eq(address2))
        }

        if (cafeId != null) {
            query.where(cafeRoomEntity.cafeId.eq(cafeId))
        }

        return query.fetch().map {
            it.toModel()
        }
    }
}
