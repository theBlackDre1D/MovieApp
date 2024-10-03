package co.init.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import co.init.core.data.Movie
import java.io.Serializable

const val MOVIE_TABLE_NAME = "movie"

@Entity(tableName = MOVIE_TABLE_NAME)
data class MovieEntity(

    @PrimaryKey val id: Int? = null,
    val adult: Boolean,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
) : Serializable {

    companion object {

        fun fromMovie(movie: Movie): MovieEntity {
            return MovieEntity(
                id = movie.id,
                adult = movie.adult,
                backdropPath = movie.backdropPath,
                originalLanguage = movie.originalLanguage,
                originalTitle = movie.originalTitle,
                overview = movie.overview,
                popularity = movie.popularity,
                posterPath = movie.posterPath,
                releaseDate = movie.releaseDate,
                title = movie.title,
                video = movie.video,
                voteAverage = movie.voteAverage,
                voteCount = movie.voteCount
            )
        }

        fun fromMovies(movies: List<Movie>): List<MovieEntity> {
            val entities = mutableListOf<MovieEntity>()
            movies.forEach { movie ->
                entities.add(fromMovie(movie))
            }

            return entities
        }
    }
}