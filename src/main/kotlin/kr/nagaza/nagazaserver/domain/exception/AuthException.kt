package kr.nagaza.nagazaserver.domain.exception

class AuthTokenNotValidException : DomainException(ErrorCode.TOKEN_NOT_VALID)
class OAuthLoginFailedException : DomainException(ErrorCode.OAUTH_LOGIN_FAILED)
class NotAuthenticatedException: DomainException(ErrorCode.NOT_AUTHENTICATED)
