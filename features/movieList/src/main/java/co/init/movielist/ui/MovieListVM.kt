package co.init.movielist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.init.core.data.Movie
import co.init.movielist.domain.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieListVM @Inject constructor(
    getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    val popularMovies: Flow<PagingData<Movie>> = getMoviesUseCase().cachedIn(viewModelScope)
}