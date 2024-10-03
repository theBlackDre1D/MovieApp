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

    fun toMovieEntities(movies: List<Movie>): List<MovieEntity> {
        val entities = mutableListOf<MovieEntity>()
        movies.forEach { movie ->
            entities.add(toMovieEntity(movie))
        }

        return entities
    }

    fun toMovie(movieEntity: MovieEntity): Movie {
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
            voteCount = movieEntity.voteCount,
            isFavorite = movieEntity.isFavorite
        )
    }

    fun toMovies(movies: List<MovieEntity>): List<Movie> {
        val entities = mutableListOf<Movie>()
        movies.forEach { movie ->
            entities.add(toMovie(movie))
        }

        return entities
    }
}