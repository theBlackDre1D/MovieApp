package co.init.movielist.domain

import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource
) {

    suspend fun getPopularMovies() = remoteDataSource.getPopularMovies()
}