# 나가자 백엔드 API서버
나가자 서비스 백엔드 API 서버 입니다.

### 기술 스택
- SpringBoot 3.1.5
- Jpa + Flyway 

### 환경 변수

| 이름             | 설명                          |
|----------------|-----------------------------|
| MYSQL_URL      | MYSQL 주소입니다 (JDBC 형태여야 합니다) |
| MYSQL_USERNAME | MYSQL 사용자 명 입니다.            |
| MYSQL_PASSWORD | MYSQL 비밀번호 입니다.             |
| AUTH_SECRET    | JWT 토큰용 시크릿 키 입니다.          |
