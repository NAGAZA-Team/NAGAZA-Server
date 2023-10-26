package kr.nagaza.nagazaserver.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .components(Components())
        .info(apiInfo())
        .servers(listOf(Server().apply {
            url = "https://api.nagaza.kr/"
            description = "기본 나가자 API 서버"
        }))

    private fun apiInfo() = Info()
        .title("나가자 API")
        .contact(Contact().apply {
            name = "CChuYong"
            email = "yeongmin1061@gmail.com"
        })
        .license(License())
        .description("나가자 API 명세입니다.")
        .version("1.0.0")
}
