package co.init.movielist.domain

import androidx.paging.PagingData
import androidx.paging.map
import co.init.core.data.Movie
import co.init.database.MovieLocalDataSource
import co.init.network.domain.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) {

    private fun getAllFavoriteMoviesIds() = localDataSource.getAllFavoriteMoviesIds()
    private fun getPopularMovies() = remoteDataSource.getPopularMovies()

    val movies: Flow<PagingData<Movie>>
        get() = combine(getPopularMovies(), getAllFavoriteMoviesIds()) { remote, favorites ->
        remote.map {
            it.copy(isFavorite = favorites.contains(it.id))
        }
    }
}