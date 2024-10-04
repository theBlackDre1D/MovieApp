package co.init.movielist.domain.useCases

import co.init.core.data.Movie
import co.init.movielist.domain.MovieRepository
import javax.inject.Inject

class RemoveFromFavoritesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(movie: Movie) = movieRepository.removeMovieFromFavorites(movie)
}