package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import kr.nagaza.nagazaserver.domain.model.CafeRoom
import kr.nagaza.nagazaserver.domain.repository.CafeRoomRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class CafeRoomRepositoryImpl(
    private val jpaCafeRoomRepository: JpaCafeRoomRepository,
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
        return jpaCafeRoomRepository.search(
            queryString = queryString,
            genre = genre,
            address1 = address1,
            address2 = address2,
            cafeId = cafeId,
        ).map {
            it.toModel()
        }
    }
}
