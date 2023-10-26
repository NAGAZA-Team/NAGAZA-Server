package kr.nagaza.nagazaserver.presenter.restapi.advice

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import jakarta.servlet.http.HttpServletRequest
import kr.nagaza.nagazaserver.domain.exception.DomainException
import kr.nagaza.nagazaserver.domain.exception.ErrorCode
import kr.nagaza.nagazaserver.presenter.restapi.dto.response.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(value = [MethodArgumentNotValidException::class, MismatchedInputException::class, MismatchedInputException::class])
    fun validationException(
        exception: Exception,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .badRequest()
            .body(
                ErrorResponse.fromErrorCode(ErrorCode.NOT_VALID_INPUT)
            )
    }

    @ExceptionHandler(value = [DomainException::class])
    fun handleDomainException(
        exception: DomainException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .badRequest()
            .body(
                ErrorResponse(
                    code = exception.code.code,
                    message = exception.code.message,
                    detail = exception.optional,
                )
            )
    }

    @ExceptionHandler
    fun unhandledException(
        exception: Exception,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        exception.printStackTrace()
        return ResponseEntity
            .badRequest()
            .body(
                ErrorResponse.fromErrorCode(ErrorCode.UNKNOWN)
            )
    }
}
