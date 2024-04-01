CREATE TABLE app_info
(
    app_version VARCHAR(255) NOT NULL,
    in_service  BIT(1)       NULL,
    CONSTRAINT pk_app_info PRIMARY KEY (app_version)
);

CREATE TABLE cafe
(
    cafe_id       VARCHAR(255) NOT NULL,
    franchise_id  VARCHAR(255) NULL,
    cafe_name     VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    address       VARCHAR(255) NULL,
    web_url       VARCHAR(255) NULL,
    phone_number  VARCHAR(255) NULL,
    location_lat  DOUBLE       NULL,
    location_lng  DOUBLE       NULL,
    addr_1        VARCHAR(255) NULL,
    addr_2        VARCHAR(255) NULL,
    CONSTRAINT pk_cafe PRIMARY KEY (cafe_id)
);

CREATE TABLE cafe_room
(
    room_id        VARCHAR(255) NOT NULL,
    cafe_id        VARCHAR(255) NULL,
    title          VARCHAR(255) NULL,
    `description`  VARCHAR(255) NULL,
    timeout        INT          NULL,
    recommend_user INT          NULL,
    room_img_url   VARCHAR(255) NULL,
    created_at     datetime     NULL,
    updated_at     datetime     NULL,
    CONSTRAINT pk_cafe_room PRIMARY KEY (room_id)
);

CREATE TABLE cafe_room_genre
(
    genre_id VARCHAR(255) NOT NULL,
    room_id  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_cafe_room_genre PRIMARY KEY (genre_id, room_id)
);

CREATE TABLE cafe_room_like
(
    room_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_cafe_room_like PRIMARY KEY (room_id, user_id)
);

CREATE TABLE cafe_room_review
(
    review_id        VARCHAR(255) NOT NULL,
    room_id          VARCHAR(255) NULL,
    user_id          VARCHAR(255) NULL,
    detail_review_id VARCHAR(255) NULL,
    created_at       datetime     NULL,
    CONSTRAINT pk_cafe_room_review PRIMARY KEY (review_id)
);

CREATE TABLE cafe_room_review_det
(
    review_id     VARCHAR(255) NOT NULL,
    user_cnt      INT          NULL,
    is_cleared    BIT(1)       NULL,
    is_life_theme BIT(1)       NULL,
    used_hint_cnt INT          NULL,
    content       VARCHAR(255) NULL,
    CONSTRAINT pk_cafe_room_review_det PRIMARY KEY (review_id)
);

CREATE TABLE cafe_room_review_rating_field
(
    rating_field_id VARCHAR(255) NOT NULL,
    review_id       VARCHAR(255) NULL,
    type            TINYINT      NULL,
    value           INT          NULL,
    CONSTRAINT pk_cafe_room_review_rating_field PRIMARY KEY (rating_field_id)
);

CREATE TABLE genre
(
    genre_id VARCHAR(255) NOT NULL,
    name     VARCHAR(255) NULL,
    CONSTRAINT pk_genre PRIMARY KEY (genre_id)
);

CREATE TABLE social_user
(
    social_provider   VARCHAR(255) NOT NULL,
    social_identifier VARCHAR(255) NOT NULL,
    user_id           VARCHAR(255) NULL,
    CONSTRAINT pk_social_user PRIMARY KEY (social_provider, social_identifier)
);

CREATE TABLE user
(
    user_id         VARCHAR(255) NOT NULL,
    nickname        VARCHAR(255) NULL,
    profile_img_url VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

ALTER TABLE cafe_room_review
    ADD CONSTRAINT FK_CAFE_ROOM_REVIEW_ON_DETAIL_REVIEW FOREIGN KEY (detail_review_id) REFERENCES cafe_room_review_det (review_id);

ALTER TABLE cafe_room_review
    ADD CONSTRAINT FK_CAFE_ROOM_REVIEW_ON_ROOM FOREIGN KEY (room_id) REFERENCES cafe_room (room_id);

ALTER TABLE cafe_room_review_rating_field
    ADD CONSTRAINT FK_CAFE_ROOM_REVIEW_RATING_FIELD_ON_REVIEW FOREIGN KEY (review_id) REFERENCES cafe_room_review (review_id);

ALTER TABLE cafe_room_genre
    ADD CONSTRAINT fk_cafroogen_on_cafe_room_entity FOREIGN KEY (room_id) REFERENCES cafe_room (room_id);

ALTER TABLE cafe_room_genre
    ADD CONSTRAINT fk_cafroogen_on_genre_entity FOREIGN KEY (genre_id) REFERENCES genre (genre_id);

INSERT INTO user (user_id, nickname, profile_img_url)
VALUES ('01HDNFJHCNS5E2W35YTB030TJ8', '테스트용사용자',
        'https://cdn.pixabay.com/photo/2016/08/08/09/17/avatar-1577909_960_720.png');
