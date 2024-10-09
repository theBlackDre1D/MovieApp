package co.init.movielist.ui.components.movieListItem

import androidx.lifecycle.ViewModel
import co.init.core.data.Movie
import co.init.database.domain.IsFavoriteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListItemVM @Inject constructor(
    private val isFavoriteMovieUseCase: IsFavoriteMovieUseCase
) : ViewModel() {

    fun isFavorite(movie: Movie) = isFavoriteMovieUseCase(movie.id)
}