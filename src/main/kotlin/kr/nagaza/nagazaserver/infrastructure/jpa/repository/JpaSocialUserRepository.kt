package kr.nagaza.nagazaserver.infrastructure.jpa.repository

import kr.nagaza.nagazaserver.infrastructure.jpa.entity.SocialUserEntity
import kr.nagaza.nagazaserver.infrastructure.jpa.entity.SocialUserEntityKey
import org.springframework.data.jpa.repository.JpaRepository

interface JpaSocialUserRepository: JpaRepository<SocialUserEntity, SocialUserEntityKey>
