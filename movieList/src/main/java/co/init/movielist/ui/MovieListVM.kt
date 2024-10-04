package co.init.movielist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.init.core.data.Movie
import co.init.core.extensions.doInIOCoroutine
import co.init.movielist.domain.useCases.AddToFavoritesUseCase
import co.init.movielist.domain.useCases.GetFavoriteMoviesUseCase
import co.init.movielist.domain.useCases.GetPopularMoviesUseCase
import co.init.movielist.domain.useCases.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieListVM @Inject constructor(
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : ViewModel() {

    val popularMovies: Flow<PagingData<Movie>> = getPopularMoviesUseCase().cachedIn(viewModelScope)
    val favoriteMovies: Flow<PagingData<Movie>> = getFavoriteMoviesUseCase().cachedIn(viewModelScope)

    fun onFavoriteIconClick(movie: Movie) {
        if (movie.isFavorite) {
            removeFromFavorites(movie)
        } else {
            addToFavorites(movie)
        }
    }

    private fun addToFavorites(movie: Movie) {
        doInIOCoroutine {
            addToFavoritesUseCase(movie).collect { }
        }
    }

    private fun removeFromFavorites(movie: Movie) {
        doInIOCoroutine {
            removeFromFavoritesUseCase(movie).collect { }
        }
    }
}