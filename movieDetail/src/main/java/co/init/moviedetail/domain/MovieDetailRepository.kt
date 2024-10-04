package co.init.moviedetail.domain

import co.init.core.data.Movie
import co.init.database.EntityMapper
import co.init.database.domain.MovieLocalDataSource
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(
    private val localDataSource: MovieLocalDataSource
) {
    suspend fun addFavoriteMovie(movie: Movie) = localDataSource.addToFavorites(EntityMapper.toMovieEntity(movie.copy(isFavorite = true)))
    suspend fun removeMovieFromFavorites(movie: Movie) = localDataSource.removeFromFavorites(
        EntityMapper.toMovieEntity(movie.copy(isFavorite = true)))
}