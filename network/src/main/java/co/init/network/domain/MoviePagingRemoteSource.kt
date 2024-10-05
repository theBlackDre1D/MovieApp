package co.init.network.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.init.core.data.Movie
import co.init.network.MovieService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviePagingRemoteSource @Inject constructor(
    private val movieService: MovieService
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return try {
            val response = movieService.getPopularMovies(page = page)
            if (response.isSuccessful) {
                val movies = response.body()?.results.orEmpty()
                val nextKey = if (page < (response.body()?.totalPages ?: 0)) page + 1 else null
                val prevKey = if (page == 1) null else page - 1

                LoadResult.Page(
                    data = movies,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception(response.message()))
            }
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}