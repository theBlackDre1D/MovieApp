package co.init.database

import co.init.core.data.Movie

object EntityMapper {

    fun toMovieEntity(movie: Movie): MovieEntity {
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
            voteCount = movie.voteCount,
            isFavorite = movie.isFavorite
        )
    }
}