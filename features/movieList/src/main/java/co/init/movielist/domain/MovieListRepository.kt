package co.init.movielist.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import co.init.core.data.Movie
import co.init.database.domain.MovieDao
import co.init.network.MovieService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao
) {

    fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesMediator(movieService, movieDao) }
        ).flow
    }
}