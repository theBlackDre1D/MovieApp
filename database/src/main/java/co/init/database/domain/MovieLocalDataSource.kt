package co.init.database.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import co.init.database.data.MovieEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    fun getFavoriteMovies() = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { movieDao.getAllFavoriteMovies() }
    ).flow

    suspend fun getAllFavoriteMoviesIds(): List<Int> = try {
        movieDao.getAllFavoriteMoviesIds()
    } catch (t: Throwable) {
        listOf()
    }

    fun toggleMovieFavoriteStatus(movieEntity: MovieEntity) = flow {
        if (movieEntity.isFavorite) {
            emit(Result.success(movieDao.deleteMovie(movieEntity)))
        } else {
            emit(Result.success(movieDao.saveMovie(movieEntity.copy(isFavorite = true))))
        }
    }.catch {
        emit(Result.failure(it))
    }
}