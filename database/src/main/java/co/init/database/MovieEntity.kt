package co.init.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

const val MOVIE_TABLE_NAME = "movie"

@Entity(tableName = MOVIE_TABLE_NAME)
data class MovieEntity(

    @PrimaryKey val id: Int? = null,
    val adult: Boolean,
//    val backdropPath: String,
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
    val isFavorite: Boolean
) : Serializable