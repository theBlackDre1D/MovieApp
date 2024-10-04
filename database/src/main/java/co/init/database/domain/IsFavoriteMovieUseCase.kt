package co.init.database.domain

import javax.inject.Inject

class IsFavoriteMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(movieId: Int) = repository.isFavoriteMovie(movieId)
}