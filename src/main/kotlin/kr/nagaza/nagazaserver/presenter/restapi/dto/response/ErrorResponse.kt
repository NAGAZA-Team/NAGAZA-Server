package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import kr.nagaza.nagazaserver.domain.exception.ErrorCode

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
