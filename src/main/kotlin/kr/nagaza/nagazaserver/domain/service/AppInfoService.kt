package kr.nagaza.nagazaserver.domain.service

import kr.nagaza.nagazaserver.infrastructure.jpa.repository.JpaAppInfoRepository
import org.springframework.stereotype.Service

@Service
class AppInfoService(
    private val jpaAppInfoRepository: JpaAppInfoRepository,
) {
    private val appInfoMap = mutableMapOf<String, Boolean>()
    fun updateAppInfo() {
        val result = jpaAppInfoRepository.findAll()
        result.forEach {
            appInfoMap[it.appVersion] = it.inService
        }
    }

    fun isInService(version: String): Boolean {
        return appInfoMap[version] == true
    }
}
