ALTER TABLE cafe_room_genre DROP CONSTRAINT fk_cafroogen_on_genre_entity;
ALTER TABLE genre MODIFY genre_id INT NOT NULL;


ALTER TABLE genre DROP PRIMARY KEY;
ALTER TABLE genre ADD PRIMARY KEY (genre_id);

ALTER TABLE cafe_room_genre MODIFY genre_id INT NOT NULL;
ALTER TABLE cafe_room_genre
    ADD CONSTRAINT fk_cafroogen_on_genre_entity FOREIGN KEY (genre_id) REFERENCES genre (genre_id);
