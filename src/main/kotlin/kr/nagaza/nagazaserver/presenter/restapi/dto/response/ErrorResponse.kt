package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import kr.nagaza.nagazaserver.domain.exception.ErrorCode

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ErrorResponse(
    val code: String,
    val message: String,
    val detail: String? = null,
) {
    companion object {
        fun fromErrorCode(code: ErrorCode) = ErrorResponse(
            code = code.code,
            message = code.message,
        )
    }
}
