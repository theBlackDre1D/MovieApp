package co.init.database.domain

import co.init.database.MovieDao
import co.init.database.MovieEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    fun addToFavorites(movieEntity: MovieEntity)  = flow {
        emit(Result.success(movieDao.saveMovie(movieEntity)))
    }.catch {
        emit(Result.failure(it))
    }

    fun removeFromFavorites(movieEntity: MovieEntity)  = flow {
        emit(Result.success(movieDao.deleteMovie(movieEntity)))
    }.catch {
        emit(Result.failure(it))
    }

    fun isFavoriteMovie(movieId: Int) = flow {
        emit(movieDao.isMovieFavorite(movieId))
    }.catch {
        emit(false)
    }
}