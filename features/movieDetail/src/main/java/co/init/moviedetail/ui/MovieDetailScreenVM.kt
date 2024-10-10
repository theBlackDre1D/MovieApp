package co.init.moviedetail.ui

import androidx.lifecycle.ViewModel
import co.init.core.data.Movie
import co.init.core.extensions.doInIOCoroutine
import co.init.moviedetail.domain.AddToFavoritesUseCase
import co.init.moviedetail.domain.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieDetailScreenVM @Inject constructor(
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : ViewModel() {

    lateinit var currentMovie: Movie

    private val _isFavoriteFlow: MutableStateFlow<Boolean> by lazy { MutableStateFlow(currentMovie.isFavorite) }
    val isFavoriteFlow: StateFlow<Boolean> get() = _isFavoriteFlow
//
//    fun checkIsFavorite(movie: Movie) {
//        doInIOCoroutine {
//            isFavoriteMovieUseCase(movie.id).collect { isFavorite ->
//                isFavoriteFlow.update { isFavorite }
//            }
//        }
//    }

    fun onFavoriteIconClick(currentFavoriteState: Boolean, movie: Movie) {
        doInIOCoroutine {
            if (currentFavoriteState) {
                removeFromFavoritesUseCase(movie).collect { result ->
                    result.onSuccess {
                        _isFavoriteFlow.update { false }
                    }
                }

            } else {
                addToFavoritesUseCase(movie).collect { result ->
                    result.onSuccess {
                        _isFavoriteFlow.update { true }
                    }
                }
            }
        }
    }
}