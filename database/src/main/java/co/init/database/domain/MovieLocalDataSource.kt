package co.init.database.domain

import co.init.database.data.MovieEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    suspend fun getAllFavoriteMoviesIds(): List<Int> = try {
        movieDao.getAllFavoriteMoviesIds()
    } catch (t: Throwable) {
        listOf()
    }

    fun toggleMovieFavoriteStatus(currentFavoriteStatus: Boolean, movieEntity: MovieEntity) = flow {
        if (currentFavoriteStatus) {
            emit(Result.success(movieDao.deleteMovie(movieEntity)))
        } else {
            emit(Result.success(movieDao.saveMovie(movieEntity.copy(isFavorite = true))))
        }
    }.catch {
        emit(Result.failure(it))
    }
}