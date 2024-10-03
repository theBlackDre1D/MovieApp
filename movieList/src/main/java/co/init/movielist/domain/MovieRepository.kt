package co.init.movielist.domain

import co.init.core.data.Movie
import co.init.database.EntityMapper
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) {

    fun getPopularMovies() = remoteDataSource.getPopularMovies()
    suspend fun getSavedMovies() = localDataSource.getAllFavoriteMovies()
    suspend fun addFavoriteMovie(movie: Movie) = localDataSource.addToFavorites(EntityMapper.toMovieEntity(movie.copy(isFavorite = true)))
}