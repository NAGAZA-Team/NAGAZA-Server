package kr.nagaza.nagazaserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {
    @Bean("oAuthClient")
    fun oAuthClient(): WebClient {
        return WebClient
            .builder()
            .build()
    }
}
