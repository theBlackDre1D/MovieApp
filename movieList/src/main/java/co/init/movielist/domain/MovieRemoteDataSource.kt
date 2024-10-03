package co.init.movielist.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import co.init.core.base.BaseRemoteSource
import co.init.core.data.Movie
import co.init.network.MovieService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val service: MovieService
) : BaseRemoteSource() {

    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, // TMDb default is 20
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(service) }
        ).flow
    }
}