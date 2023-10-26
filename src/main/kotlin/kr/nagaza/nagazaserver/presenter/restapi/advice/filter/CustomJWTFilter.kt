package kr.nagaza.nagazaserver.presenter.restapi.advice.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.nagaza.nagazaserver.domain.repository.TokenProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class CustomJWTFilter(
    @Value("\${app.headers.authToken}") private val authTokenHeaderName: String,
    private val tokenProvider: TokenProvider,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val accessToken = request.getHeader(authTokenHeaderName)
        val retrievedId = accessToken?.let { tokenProvider.getUserIdFromToken(it) }
        if (retrievedId != null) {
            val authentication = APIKeyAuthentication(
                userId = retrievedId,
                token = accessToken,
            )
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val path = request.servletPath
        return path == "/health-check" || path.startsWith("/actuator/") || path.startsWith("/v1/auth")
    }
}
