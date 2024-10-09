package co.init.movielist.ui

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import co.init.core.data.Movie
import co.init.movielist.domain.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieListVM @Inject constructor(
    getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    val popularMovies: Flow<PagingData<Movie>> = getPopularMoviesUseCase()
}