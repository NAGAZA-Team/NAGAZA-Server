package kr.nagaza.nagazaserver.domain.exception

enum class ErrorCode(
    val code: String,
    val message: String,
) {
    UNKNOWN("CM0001", "알 수 없는 오류가 발생하였습니다."),
    NOT_VALID_INPUT("CM0002", "입력값이 올바르지 않습니다."),
    NOT_AUTHORIZED("CM0003", "인증되지 않은 사용자입니다."),
    UNAUTHENTICATED("CM0004", "로그인이 필요합니다."),

    TOKEN_NOT_VALID("AE0001", "토큰이 올바르지 않거나 만료되었습니다."),
    OAUTH_LOGIN_FAILED("AE0002", "oAuth 인증에 실패하였습니다"),

    USER_NOT_FOUND("UE0001", "사용자를 찾을 수 없습니다.")
}
