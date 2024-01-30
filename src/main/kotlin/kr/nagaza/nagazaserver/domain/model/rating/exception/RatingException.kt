import kr.nagaza.nagazaserver.domain.exception.DomainException
import kr.nagaza.nagazaserver.domain.exception.ErrorCode

class InvalidRatingValueException : DomainException(ErrorCode.INVALID_RATING_VALUE)
