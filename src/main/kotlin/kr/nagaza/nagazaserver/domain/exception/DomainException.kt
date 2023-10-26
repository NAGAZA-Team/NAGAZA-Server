package kr.nagaza.nagazaserver.domain.exception

abstract class DomainException(val code: ErrorCode, val optional: String? = null) : RuntimeException(code.message)

class NotValidInputException(optional: String? = null) : DomainException(ErrorCode.NOT_VALID_INPUT, optional = optional)
