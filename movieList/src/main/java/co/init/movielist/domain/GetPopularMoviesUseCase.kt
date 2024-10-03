package co.init.movielist.domain

import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke() = repository.getPopularMovies()
}