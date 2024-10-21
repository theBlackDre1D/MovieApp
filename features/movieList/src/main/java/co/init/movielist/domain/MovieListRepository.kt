package co.init.movielist.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import co.init.core.data.Movie
import co.init.database.domain.MovieLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) {

    fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesMediator(movieRemoteDataSource, movieLocalDataSource) }
        ).flow
    }
}