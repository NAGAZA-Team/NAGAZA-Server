package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import kr.nagaza.nagazaserver.infrastructure.jpa.entity.AppInfoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaAppInfoRepository : JpaRepository<AppInfoEntity, String>
