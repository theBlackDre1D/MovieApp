package co.init.movielist.ui

import androidx.lifecycle.ViewModel
import co.init.movielist.domain.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListVM @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    val text = "MovieListScreen"
}