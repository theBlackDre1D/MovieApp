package co.init.movielist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.init.core.data.Movie
import co.init.movielist.domain.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    val popularMovies: Flow<PagingData<Movie>> = getPopularMoviesUseCase().cachedIn(viewModelScope)
}