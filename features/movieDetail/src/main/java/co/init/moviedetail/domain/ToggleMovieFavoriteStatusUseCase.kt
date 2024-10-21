package co.init.moviedetail.domain

import co.init.core.data.Movie
import javax.inject.Inject

class ToggleMovieFavoriteStatusUseCase @Inject constructor(
    private val repository: MovieDetailRepository
) {

    operator fun invoke(currentMovieStatus: Boolean, movie: Movie) = repository.toggleMovieFavoriteStatus(currentMovieStatus, movie)
}