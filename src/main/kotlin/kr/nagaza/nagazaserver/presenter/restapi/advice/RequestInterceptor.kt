package kr.nagaza.nagazaserver.presenter.restapi.advice

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class RequestInterceptor(
    @Value("\${app.headers.appVersion}") private val appVersionHeaderName: String,
    @Value("\${app.headers.osVersion}") private val osVersionHeaderName: String,
) : HandlerInterceptor {

    companion object {
        const val START_TIME_ATTR_NAME = "startTime"
    }

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
    ): Boolean {
        val startTime = System.currentTimeMillis()
        request.setAttribute(START_TIME_ATTR_NAME, startTime)
        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?,
    ) {
        if (request.requestURI == "/api/health-check") return
        val startTime = request.getAttribute(START_TIME_ATTR_NAME) as Long
        val endTime = System.currentTimeMillis()
        val origin = request.getHeader("X-Forwarded-For") ?: request.remoteAddr
        val executionTime = endTime - startTime

        val appVersion = request.getHeader(appVersionHeaderName) ?: "UNKNOWN VERSION"
        val osVersion = request.getHeader(osVersionHeaderName) ?: "UNKNOWN OS"

        log.info(
            "[{}] [{}] {} {} {} {} {}ms",
            appVersion,
            osVersion,
            request.method,
            request.requestURI,
            response.status,
            origin,
            executionTime,
        )
    }
}
