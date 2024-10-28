package co.init.moviedetail.ui

import co.init.core.base.BaseVM
import co.init.core.data.Movie
import co.init.core.extensions.doInIOCoroutine
import co.init.database.domain.useCases.ToggleMovieFavoriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieDetailScreenVM @Inject constructor(
    private val toggleMovieFavoriteStatusUseCase: ToggleMovieFavoriteStatusUseCase
) : BaseVM() {

    private val _state: MutableStateFlow<State> by lazy { MutableStateFlow(State()) }
    val state get() = _state.asStateFlow()

    data class State(
        val movie: Movie? = null,
        val errorMessage: String? = null
    )

    sealed class Intent {
        data class LoadMovie(val movie: Movie) : Intent()
        data object LikeMovie : Intent()
    }

    fun handleIntent(intent: Intent) {
        when (intent) {
            Intent.LikeMovie -> onFavoriteIconClick()
            is Intent.LoadMovie -> {
                if (_state.value.movie == null) {
                    _state.update { it.copy(movie = intent.movie) }
                }
            }
        }
    }

    private fun onFavoriteIconClick() {
        _state.value.movie?.let { movie ->
            doInIOCoroutine {
                toggleMovieFavoriteStatusUseCase(movie).collect { result ->
                    wrapResult(result) {
                        val newMovie = movie.copy(isFavorite = !movie.isFavorite)
                        _state.update { it.copy(movie = newMovie) }
                    }
                }
            }
        }
    }
}