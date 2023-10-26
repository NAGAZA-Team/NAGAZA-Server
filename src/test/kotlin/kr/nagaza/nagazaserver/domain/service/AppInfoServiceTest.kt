package kr.nagaza.nagazaserver.domain.service

import kr.nagaza.nagazaserver.infrastructure.jpa.entity.AppInfoEntity
import kr.nagaza.nagazaserver.infrastructure.jpa.repository.JpaAppInfoRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExtendWith(MockitoExtension::class)
class AppInfoServiceTest {
    @Test
    fun `updateAppInfo() & isInService() 테스트`() {
        // given
        val appInfoEntity = AppInfoEntity(
            appVersion = "1.0.0",
            inService = true,
        )
        val jpaRepository = mock<JpaAppInfoRepository>() {
            on { findAll() } doReturn listOf(appInfoEntity)
        }
        val appInfoService = AppInfoService(jpaRepository)
        // when
        appInfoService.updateAppInfo()
        // then
        assert(appInfoService.isInService("1.0.0"))
    }
}
