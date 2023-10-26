package kr.nagaza.nagazaserver.presenter.restapi.advice.filter

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.nagaza.nagazaserver.domain.exception.ErrorCode
import kr.nagaza.nagazaserver.domain.service.AppInfoService
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.ErrorResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class ApplicationFilter(
    private val objectMapper: ObjectMapper,
    private val appInfoService: AppInfoService,
    @Value("\${app.headers.appVersion}") private val appVersionHeaderName: String,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val appVersion = request.getHeader(appVersionHeaderName) ?: ""
        if (appInfoService.isInService(appVersion)) {
            writeUpdateResponse(response)
            return
        }

        filterChain.doFilter(request, response)
    }

    @Throws(IOException::class)
    private fun writeUpdateResponse(response: HttpServletResponse) {
        response.status = HttpStatus.UPGRADE_REQUIRED.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE

        response.outputStream.use { os ->
            objectMapper.writeValue(
                os, ErrorResponse(
                    code = ErrorCode.APP_VERSION_EXCEPTION.code,
                    message = ErrorCode.APP_VERSION_EXCEPTION.message,
                )
            )
            os.flush()
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.getHeader(appVersionHeaderName).isNullOrEmpty()
    }
}
