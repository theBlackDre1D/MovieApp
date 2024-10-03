package co.init.database

import co.init.core.data.Movie

object EntityMapper {

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

    fun fromMovieEntity(movieEntity: MovieEntity): Movie {
        return Movie(
            id = movieEntity.id!!,
            adult = movieEntity.adult,
            backdropPath = movieEntity.backdropPath,
            originalLanguage = movieEntity.originalLanguage,
            originalTitle = movieEntity.originalTitle,
            overview = movieEntity.overview,
            popularity = movieEntity.popularity,
            posterPath = movieEntity.posterPath,
            releaseDate = movieEntity.releaseDate,
            title = movieEntity.title,
            video = movieEntity.video,
            voteAverage = movieEntity.voteAverage,
            voteCount = movieEntity.voteCount
        )
    }

    fun fromMovieEntities(movies: List<MovieEntity>): List<Movie> {
        val entities = mutableListOf<Movie>()
        movies.forEach { movie ->
            entities.add(fromMovieEntity(movie))
        }

        return entities
    }
}