package co.init.network

import co.init.core.data.Movie
import co.init.core.data.PageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): Response<PageResponse<Movie>>
}