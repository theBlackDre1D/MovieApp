package co.init.movielist.domain

import co.init.core.data.Movie
import co.init.core.data.PageResponse
import co.init.network.MovieService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val service: MovieService
) {

    suspend fun getPopularMovies() = flow<Result<PageResponse<Movie>>> {
        val response = service.getPopularMovies()
        if (response.isSuccessful) {
            response.body()?.let { body ->
                emit(Result.success(body))
            }
        } else {
            emit(Result.failure(Throwable("Some error occoured"))) // TODO better throwable
        }
    }.catch {
        emit(Result.failure(it))
    }
}