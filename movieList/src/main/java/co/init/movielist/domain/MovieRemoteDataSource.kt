package co.init.movielist.domain

import co.init.core.base.BaseRemoteSource
import co.init.network.MovieService
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val service: MovieService
) : BaseRemoteSource() {

    suspend fun getPopularMovies() = executeCall { service.getPopularMovies() }
}