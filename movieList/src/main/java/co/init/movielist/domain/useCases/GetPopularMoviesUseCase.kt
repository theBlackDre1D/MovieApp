package co.init.movielist.domain.useCases

import co.init.movielist.domain.MovieListRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieListRepository
) {

    operator fun invoke() = repository.getPopularMovies()
}