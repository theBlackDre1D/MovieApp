package co.init.moviedetail.domain

import co.init.core.data.Movie
import javax.inject.Inject

class RemoveFromFavoritesUseCase @Inject constructor(
    private val movieRepository: MovieDetailRepository
) {

    suspend operator fun invoke(movie: Movie) = movieRepository.removeMovieFromFavorites(movie)
}