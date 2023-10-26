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
}
