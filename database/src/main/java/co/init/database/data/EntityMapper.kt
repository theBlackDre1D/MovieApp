package co.init.database.data

import co.init.core.data.Movie

object EntityMapper {

    fun toMovieEntity(movie: Movie): MovieEntity {
        return MovieEntity(
            id = movie.id,
            adult = movie.adult,
            originalLanguage = movie.originalLanguage,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            popularity = movie.popularity,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            title = movie.title,
            video = movie.video,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            isFavorite = movie.isFavorite
        )
    }

    fun toMovie(movieEntity: MovieEntity): Movie {
        return Movie(
            id = movieEntity.id!!,
            adult = movieEntity.adult,
            originalLanguage = movieEntity.originalLanguage,
            originalTitle = movieEntity.originalTitle,
            overview = movieEntity.overview,
            popularity = movieEntity.popularity,
            posterPath = movieEntity.posterPath,
            releaseDate = movieEntity.releaseDate,
            title = movieEntity.title,
            video = movieEntity.video,
            voteAverage = movieEntity.voteAverage,
            voteCount = movieEntity.voteCount,
            isFavorite = movieEntity.isFavorite
        )
    }
}