package co.init.movielist.domain

import co.init.database.domain.IHasLocalDataSource
import co.init.database.domain.MovieLocalDataSource
import co.init.network.domain.MovieRemoteDataSource
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) : IHasLocalDataSource {

    fun getPopularMovies() = remoteDataSource.getPopularMovies()
    override fun isFavoriteMovie(movieId: Int) = localDataSource.isFavoriteMovie(movieId)
}