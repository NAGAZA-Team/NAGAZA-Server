package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import com.querydsl.jpa.impl.JPAQuery
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import kr.nagaza.nagazaserver.domain.model.CafeRoom
import kr.nagaza.nagazaserver.domain.model.CafeRoomAddressMap
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
        genre: Int?,
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

    override fun getAll(): List<CafeRoom> {
        return jpaCafeRoomRepository.findAll().map {
            it.toModel()
        }
    }

    override fun getRoomCountGroupByAddress(): List<CafeRoomAddressMap> {
        val cafeRoomEntity = QCafeRoomEntity.cafeRoomEntity
        val cafeEntity = QCafeEntity.cafeEntity
        val result =
            JPAQuery<CafeRoom>(entityManager)
                .select(cafeEntity.addressOne, cafeEntity.addressTwo, cafeRoomEntity.count())
                .from(cafeRoomEntity)
                .join(cafeEntity).on(cafeRoomEntity.cafeId.eq(cafeEntity.cafeId))
                .groupBy(cafeEntity.addressOne, cafeEntity.addressTwo)
                .fetch()

        return result.filter {
            it.get(cafeEntity.addressOne) != null && it.get(cafeEntity.addressTwo) != null
        }.map {
            CafeRoomAddressMap(
                address1 = it.get(cafeEntity.addressOne)!!,
                address2 = it.get(cafeEntity.addressTwo)!!,
                count = it.get(cafeRoomEntity.count())?.toInt() ?: 0,
            )
        }
    }
}
