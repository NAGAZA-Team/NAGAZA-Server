package kr.nagaza.nagazaserver.util

object MockNickGenerator {
    private val prefix = listOf("멋있는", "맛있는", "대단한", "엄청난", "위대한", "행복한", "즐거운", "새로운", "작은", "신기한", "재밌는")
    private val suffix = listOf(
        "식사",
        "과일",
        "책",
        "커피",
        "술",
        "음악",
        "영화",
        "채팅",
        "통화",
        "코딩",
        "휴식",
        "여행",
        "운동",
        "게임",
        "독서",
        "공부",
        "숙제",
        "과제",
        "취미",
        "취업",
        "연애",
        "결혼",
        "투자",
        "프로그래밍",
        "개발",
        "코딩",
        "테스트",
        "보안"
    )

    fun generate() = "${prefix.random()}${suffix.random()}"
}
