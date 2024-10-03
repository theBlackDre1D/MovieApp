package co.init.movielist.domain

import co.init.core.data.Movie
import javax.inject.Inject

class SaveMoviesToDatabaseUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(movies: List<Movie>) = repository.savePopularMoviesToDatabase(movies)
}