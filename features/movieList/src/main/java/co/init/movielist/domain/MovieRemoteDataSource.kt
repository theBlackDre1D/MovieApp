package co.init.movielist.domain

import co.init.network.MovieService
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun getPopularMovies(page: Int) = movieService.getPopularMovies(page)
}