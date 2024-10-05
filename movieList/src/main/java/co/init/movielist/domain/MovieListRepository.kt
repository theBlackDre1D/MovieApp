package co.init.movielist.domain

import co.init.network.domain.MovieRemoteDataSource
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) {

    fun getPopularMovies() = remoteDataSource.getPopularMovies()
}