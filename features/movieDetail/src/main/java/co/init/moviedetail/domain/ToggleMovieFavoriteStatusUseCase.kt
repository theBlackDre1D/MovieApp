package co.init.moviedetail.domain

import co.init.core.data.Movie
import javax.inject.Inject

class ToggleMovieFavoriteStatusUseCase @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository
) {

    operator fun invoke(currentFavoriteStatus: Boolean, movie: Movie) = movieDetailRepository.toggleMovieFavoriteStatus(currentFavoriteStatus, movie)
}