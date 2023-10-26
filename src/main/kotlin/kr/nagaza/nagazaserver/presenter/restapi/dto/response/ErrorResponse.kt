package kr.nagaza.nagazaserver.presenter.restapi.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import kr.nagaza.nagazaserver.domain.exception.ErrorCode

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "에러 발생시 응답")
data class ErrorResponse(
    @Schema(description = "에러 코드", example = "CM0001")
    val code: String,

    @Schema(description = "에러 메시지", example = "알 수 없는 오류가 발생하였습니다.")
    val message: String,

    @Schema(description = "에러 상세 메시지", example = "[오류 발생 이유]", required = false)
    val detail: String? = null,
) {
    companion object {
        fun fromErrorCode(code: ErrorCode) = ErrorResponse(
            code = code.code,
            message = code.message,
        )
    }
}
