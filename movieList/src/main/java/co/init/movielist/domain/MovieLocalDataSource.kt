package co.init.movielist.domain

import co.init.database.MovieDao
import co.init.database.MovieEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    suspend fun getAllFavoriteMovies() = flow {
        emit(Result.success(movieDao.getAllMovies()))
    }.catch {
        emit(Result.failure(it))
    }

    suspend fun addToFavorites(movieEntity: MovieEntity)  = flow {
        emit(Result.success(movieDao.saveMovie(movieEntity)))
    }.catch {
        emit(Result.failure(it))
    }

    suspend fun removeFromFavorites(movieEntity: MovieEntity)  = flow {
        emit(Result.success(movieDao.deleteMovie(movieEntity)))
    }.catch {
        emit(Result.failure(it))
    }
}