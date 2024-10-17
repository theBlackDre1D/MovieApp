package co.init.movielist.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.init.core.data.Movie
import co.init.database.domain.MovieDao
import co.init.network.MovieService
import retrofit2.HttpException
import java.io.IOException

class MoviesMediator(
    private val movieService: MovieService,
    private val movieDao: MovieDao
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return try {
            val response = movieService.getPopularMovies(page = page)
            if (response.isSuccessful) {
                val movies = response.body()?.results.orEmpty()
                val nextKey = if (page < (response.body()?.totalPages ?: 0)) page + 1 else null
                val prevKey = if (page == 1) null else page - 1

                val favorites = movieDao.getAllFavoriteMoviesIds()

                val mappedMovies = movies.map {
                    it.copy(isFavorite = favorites.contains(it.id))
                }

                LoadResult.Page(
                    data = mappedMovies,
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