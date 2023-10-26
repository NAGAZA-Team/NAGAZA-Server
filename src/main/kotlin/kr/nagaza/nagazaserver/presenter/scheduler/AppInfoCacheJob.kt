package kr.nagaza.nagazaserver.presenter.scheduler

import jakarta.annotation.PostConstruct
import kr.nagaza.nagazaserver.domain.service.AppInfoService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class AppInfoCacheJob(
    private val appInfoService: AppInfoService,
) {
    @PostConstruct
    fun onInitialize() {
        refreshAppInfo()
    }

    @Scheduled(fixedRate = 1000L * 60)
    fun refreshAppInfo() {
        appInfoService.updateAppInfo()
    }
}
