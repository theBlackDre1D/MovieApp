package co.init.moviedetail.domain

import co.init.core.data.Movie
import co.init.database.EntityMapper
import co.init.database.domain.IIsFavoriteMovie
import co.init.database.domain.MovieLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(
    private val localDataSource: MovieLocalDataSource
) : IIsFavoriteMovie {

    fun addFavoriteMovie(movie: Movie) = localDataSource.addToFavorites(EntityMapper.toMovieEntity(movie.copy(isFavorite = true)))
    fun removeMovieFromFavorites(movie: Movie) = localDataSource.removeFromFavorites(
        EntityMapper.toMovieEntity(movie.copy(isFavorite = true)))

    override fun isFavoriteMovie(movieId: Int): Flow<Boolean> = localDataSource.isFavoriteMovie(movieId)
}