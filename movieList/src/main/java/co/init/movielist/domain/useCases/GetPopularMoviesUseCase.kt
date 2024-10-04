package co.init.movielist.domain.useCases

import co.init.movielist.domain.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke() = repository.getPopularMovies()
}