package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "app_info")
class AppInfoEntity(
    @Id
    @Column(name = "app_version")
    val appVersion: String,

    @Column(name = "in_service")
    val inService: Boolean,
)
