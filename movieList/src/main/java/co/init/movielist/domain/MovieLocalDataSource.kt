package co.init.movielist.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import co.init.database.MovieDao
import co.init.database.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    fun getAllMoviesPaged(): Flow<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { movieDao.getAllMoviesPaged() }
        ).flow
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