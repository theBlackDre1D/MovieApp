package co.init.movielist.domain.useCases

import co.init.core.data.Movie
import co.init.movielist.domain.MovieRepository
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(movie: Movie) = repository.addFavoriteMovie(movie)
}