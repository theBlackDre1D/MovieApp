package co.init.movielist.domain

import co.init.core.data.Movie
import javax.inject.Inject

class ToggleMovieFavoriteStatusUseCase @Inject constructor(
    private val movieListRepository: MovieListRepository
) {

    operator fun invoke(currentFavoriteStatus: Boolean, movie: Movie) = movieListRepository.toggleMovieFavoriteStatus(currentFavoriteStatus, movie)
}