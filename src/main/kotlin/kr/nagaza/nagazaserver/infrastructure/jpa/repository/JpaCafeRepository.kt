package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import kr.nagaza.nagazaserver.infrastructure.jpa.entity.CafeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaCafeRepository : JpaRepository<CafeEntity, String> {
    fun findAllByAddressOneAndAddressTwo(addressOne: String, addressTwo: String): List<CafeEntity>
}
