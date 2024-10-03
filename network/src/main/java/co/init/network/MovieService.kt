package co.init.network

import co.init.core.data.Movie
import co.init.core.data.PageResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<PageResponse<Movie>>
}