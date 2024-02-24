package kr.nagaza.nagazaserver.infrastructure.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import kr.nagaza.nagazaserver.domain.model.Genre

@Entity(name = "genre")
class GenreEntity(
    @Id
    @Column(name = "genre_id")
    val genreId: String,
    @Column(name = "name")
    val name: String,
) {
    companion object {
        fun fromModel(genre: Genre) =
            GenreEntity(
                genreId = genre.genreId,
                name = genre.name,
            )

        fun toModel(genreEntity: GenreEntity) =
            Genre(
                genreId = genreEntity.genreId,
                name = genreEntity.name,
            )
    }
}
