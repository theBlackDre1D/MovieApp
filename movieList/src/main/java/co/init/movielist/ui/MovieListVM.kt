package co.init.movielist.ui

import androidx.lifecycle.ViewModel
import co.init.core.data.Movie
import co.init.core.extensions.doInIOCoroutine
import co.init.core.extensions.safe
import co.init.movielist.domain.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieListVM @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    val text = "MovieListScreen"

    data class ScreenState(
        val movies: List<Movie> = listOf(),
        val loading: Boolean = false
    )

    private val _movies = MutableStateFlow(ScreenState())
    val movies: StateFlow<ScreenState> get() = _movies

    private var getMoviesJob: Job? = null

    fun initLaunch() {
        if (_movies.value.movies.isEmpty() && !movies.value.loading) {
            getPopularMovies()
        }
    }

    fun getPopularMovies() {
        if (getMoviesJob?.isActive.safe()) return

        getMoviesJob = doInIOCoroutine {
            _movies.update { it.copy(loading = true) }

            getPopularMoviesUseCase().collect { result ->
                _movies.update { it.copy(loading = false) }

                result.fold(
                    onSuccess = { success ->
                        _movies.update { it.copy(movies = it.movies + success.results) }
                    },
                    onFailure = {
                        // TODO
                    }
                )
            }
        }
    }
}