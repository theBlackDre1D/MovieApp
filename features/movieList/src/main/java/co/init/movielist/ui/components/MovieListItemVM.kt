package co.init.movielist.ui.components

import androidx.lifecycle.ViewModel
import co.init.core.data.Movie
import co.init.core.extensions.doInIOCoroutine
import co.init.core.extensions.safe
import co.init.database.domain.useCases.ToggleMovieFavoriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieListItemVM @Inject constructor(
    private val toggleMovieFavoriteStatusUseCase: ToggleMovieFavoriteStatusUseCase
) : ViewModel() {

    data class State(
        val movie: Movie? = null,
        val errorMessage: String? = null
    )

    sealed class Intent {
        data class SetMovie(val movie: Movie) : Intent()
        data object LikeMove : Intent()
    }

    private val _state = MutableStateFlow(State())
    val state get() = _state.asStateFlow()

    fun handleIntent(intent: Intent) {
        when (intent) {
            Intent.LikeMove -> likeMovie()
            is Intent.SetMovie -> setMovie(intent.movie)
        }
    }

    private fun likeMovie() {
        _state.value.movie?.let { movie ->
            doInIOCoroutine {
                toggleMovieFavoriteStatusUseCase(movie.isFavorite.safe(), movie).collect { result ->
                    result.fold(
                        onSuccess = {
                            _state.update {
                                it.copy(movie = movie.copy(isFavorite = !movie.isFavorite))
                            }
                        },
                        onFailure = { throwable ->
                            _state.update { it.copy(errorMessage = throwable.message) }
                        }
                    )
                }
            }
        }
    }

    private fun setMovie(movie: Movie) {
        _state.update {
            it.copy(movie = movie)
        }
    }
}