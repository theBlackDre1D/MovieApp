package co.init.moviedetail.domain

import co.init.core.data.Movie
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
    private val repository: MovieDetailRepository
) {

    suspend operator fun invoke(movie: Movie) = repository.addFavoriteMovie(movie)
}