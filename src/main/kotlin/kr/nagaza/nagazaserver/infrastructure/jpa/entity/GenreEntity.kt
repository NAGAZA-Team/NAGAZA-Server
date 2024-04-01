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
    @Column(name = "name", nullable = false)
    val name: String,
) {
    fun toModel(): Genre {
        return Genre(
            genreId = genreId,
            name = name,
        )
    }

    companion object {
        fun fromModel(genre: Genre): GenreEntity {
            return GenreEntity(
                genreId = genre.genreId,
                name = genre.name,
            )
        }
    }
}
