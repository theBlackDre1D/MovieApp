package co.init.moviedetail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import co.init.core.data.Movie
import co.init.core.extensions.doInIOCoroutine
import co.init.moviedetail.domain.AddToFavoritesUseCase
import co.init.moviedetail.domain.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailVM @Inject constructor(
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val movie = savedStateHandle.toRoute<Movie>()

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