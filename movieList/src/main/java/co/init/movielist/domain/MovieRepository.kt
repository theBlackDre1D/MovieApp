package co.init.movielist.domain

import co.init.core.data.Movie
import co.init.database.EntityMapper
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) {

    fun getPopularMovies() = remoteDataSource.getPopularMovies()
    fun getAllFavoriteMovies() = localDataSource.getAllMoviesPaged()
    suspend fun addFavoriteMovie(movie: Movie) = localDataSource.addToFavorites(EntityMapper.toMovieEntity(movie.copy(isFavorite = true)))
    suspend fun removeMovieFromFavorites(movie: Movie) = localDataSource.removeFromFavorites(EntityMapper.toMovieEntity(movie.copy(isFavorite = true)))
}