CREATE TABLE user
(
    user_id         CHAR(26)    NOT NULL,
    nickname        VARCHAR(64) NOT NULL,
    profile_img_url VARCHAR(128),
    created_at      DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci comment '유저테이블';

CREATE TABLE social_user
(
    social_provider   VARCHAR(16)  NOT NULL,
    social_identifier VARCHAR(128) NOT NULL,
    user_id           CHAR(26)     NOT NULL,
    created_at        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (social_provider, social_identifier),
    FOREIGN KEY social_user_fk1 (user_id) REFERENCES user (user_id) ON DELETE CASCADE
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci comment '소셜로그인';

CREATE TABLE cafe_franchise
(
    franchise_id   CHAR(26)    NOT NULL,
    franchise_name VARCHAR(64) NOT NULL,
    PRIMARY KEY (franchise_id)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci comment '프랜차이즈';

CREATE TABLE cafe
(
    cafe_id      CHAR(26)     NOT NULL,
    franchise_id CHAR(26)              DEFAULT NULL,
    cafe_name    VARCHAR(128) NOT NULL,
    description  TEXT         NOT NULL DEFAULT '',
    address      VARCHAR(255),
    web_url      VARCHAR(255),
    phone_number VARCHAR(36),
    location_lat DOUBLE,
    location_lng DOUBLE,
    addr_1     VARCHAR(16),
    addr_2     VARCHAR(16),
    PRIMARY KEY (cafe_id),
    FOREIGN KEY cafe_fk1 (franchise_id) REFERENCES cafe_franchise (franchise_id) ON DELETE SET NULL
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci comment '방탈출카페';

CREATE TABLE cafe_room
(
    room_id        CHAR(26)    NOT NULL,
    cafe_id        CHAR(26)    NOT NULL,
    genre          VARCHAR(16) NOT NULL,
    timeout        INT         NOT NULL,
    recommend_user INT         NOT NULL,
    room_img_url   VARCHAR(128),
    description    TEXT        NOT NULL DEFAULT '',
    PRIMARY KEY (room_id),
    FOREIGN KEY cafe_room_fk1 (cafe_id) REFERENCES cafe (cafe_id) ON DELETE CASCADE
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci comment '방탈출카페방';

CREATE TABLE cafe_room_like
(
    room_id    CHAR(26) NOT NULL,
    user_id    CHAR(26) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (room_id, user_id),
    FOREIGN KEY cafe_room_like_fk1 (room_id) REFERENCES cafe_room (room_id) ON DELETE CASCADE,
    FOREIGN KEY cafe_room_like_fk2 (user_id) REFERENCES user (user_id) ON DELETE CASCADE
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci comment '방탈출카페좋아요';

CREATE TABLE cafe_room_review
(
    review_id  CHAR(26) NOT NULL,
    room_id    CHAR(26) NOT NULL,
    user_id    CHAR(26) NOT NULL,
    content    TEXT     NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (review_id),
    FOREIGN KEY cafe_room_review_fk1 (room_id) REFERENCES cafe_room (room_id),
    FOREIGN KEY cafe_room_review_fk2 (user_id) REFERENCES user (user_id)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci comment '방탈출카페방리뷰';

CREATE TABLE cafe_room_review_det
(
    review_id        CHAR(26) NOT NULL,
    user_cnt         INT,
    is_cleared       BOOL,
    is_life_theme    BOOL,
    used_hint_cnt    INT,
    difficulty_point INT,
    activity_point   INT,
    interior_point   INT,
    production_point INT,
    device_ratio     DOUBLE,
    PRIMARY KEY (review_id),
    FOREIGN KEY cafe_room_review_det_fk1 (review_id) REFERENCES cafe_room_review (review_id) ON DELETE CASCADE
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci comment '방탈출카페방리뷰디테일';

CREATE TABLE cafe_room_review_det_opt
(
    review_id    CHAR(26)    NOT NULL,
    option_type  VARCHAR(16) NOT NULL,
    option_value BOOL        NOT NULL,
    PRIMARY KEY (review_id, option_type),
    FOREIGN KEY cafe_room_review_det_opt_fk1 (review_id) REFERENCES cafe_room_review (review_id) ON DELETE CASCADE
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci comment '방탈출카페방리뷰디테일선택';

# MOCK DML
INSERT INTO user (user_id, nickname, profile_img_url) VALUES ('01HDNFJHCNS5E2W35YTB030TJ8', '테스트용사용자', 'https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png');
