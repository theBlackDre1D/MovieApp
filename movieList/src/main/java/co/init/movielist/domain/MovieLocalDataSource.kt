package co.init.movielist.domain

import co.init.database.MovieDao
import co.init.database.MovieEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    suspend fun getAllMovies() = flow {
        emit(Result.success(movieDao.getAllMovies()   ))
    }.catch {
        emit(Result.failure(it))
    }

    suspend fun saveMovies(movies: List<MovieEntity>) = flow {
        emit(Result.success(movieDao.saveMovies(movies)))
    }.catch {
        emit(Result.failure(it))
    }
}