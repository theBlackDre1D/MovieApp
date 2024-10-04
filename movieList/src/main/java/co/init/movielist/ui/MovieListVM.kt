package co.init.movielist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.init.core.data.Movie
import co.init.database.domain.IsFavoriteMovieUseCase
import co.init.movielist.domain.GetFavoriteMoviesUseCase
import co.init.movielist.domain.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieListVM @Inject constructor(
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val isFavoriteMovieUseCase: IsFavoriteMovieUseCase
) : ViewModel() {

    val popularMovies: Flow<PagingData<Movie>> = getPopularMoviesUseCase().cachedIn(viewModelScope)
    val favoriteMovies: Flow<PagingData<Movie>> = getFavoriteMoviesUseCase().cachedIn(viewModelScope)

    fun isFavorite(movie: Movie) = isFavoriteMovieUseCase(movie.id)
}