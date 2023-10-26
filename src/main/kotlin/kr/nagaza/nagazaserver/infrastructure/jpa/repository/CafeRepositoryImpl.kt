package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import kr.nagaza.nagazaserver.domain.model.Cafe
import kr.nagaza.nagazaserver.domain.repository.CafeRepository
import kr.nagaza.nagazaserver.infrastructure.jpa.entity.CafeEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class CafeRepositoryImpl(
    private val jpaCafeRepository: JpaCafeRepository,
) : CafeRepository {
    override fun findAll(): List<Cafe> {
        return jpaCafeRepository.findAll().map { it.toModel() }
    }

    override fun findById(id: String): Cafe? {
        return jpaCafeRepository.findByIdOrNull(id)?.toModel()
    }

    override fun saveCafe(cafe: Cafe): Cafe {
        return jpaCafeRepository.save(CafeEntity.fromModel(cafe)).toModel()
    }

}
