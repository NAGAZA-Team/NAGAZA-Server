package kr.nagaza.nagazaserver.config

import kr.nagaza.nagazaserver.presenter.restapi.advice.filter.ApplicationFilter
import kr.nagaza.nagazaserver.presenter.restapi.advice.filter.AuthEntryPoint
import kr.nagaza.nagazaserver.presenter.restapi.advice.filter.CustomJWTFilter
import kr.nagaza.nagazaserver.presenter.restapi.advice.filter.WebAccessDeniedHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class WebSecurityConfig(
    private val jwtFilter: CustomJWTFilter,
    private val applicationFilter: ApplicationFilter,
    private val authEntryPoint: AuthEntryPoint,
    private val webAccessDeniedHandler: WebAccessDeniedHandler,
) {
    @Bean
    fun config(http: HttpSecurity): SecurityFilterChain {
        return http
            .headers {
                it.frameOptions { frameOption ->
                    frameOption.sameOrigin()
                }
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/v1/auth/**").permitAll()
                    .requestMatchers("/health-check").permitAll()
                    .requestMatchers("/actuator/**").permitAll()
                    //-- Swagger Security Config Start
                    .requestMatchers("/swagger-ui/**").permitAll()
                    .requestMatchers("/swagger-ui.html").permitAll()
                    .requestMatchers("/v3/api-docs/**").permitAll()
                    //-- Swagger Security Config end
                    .anyRequest().authenticated()
            }
            .csrf {
                it.disable()
            }
            .addFilterBefore(applicationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                it.authenticationEntryPoint(authEntryPoint)
                    .accessDeniedHandler(webAccessDeniedHandler)
            }
            .build()
    }
}
