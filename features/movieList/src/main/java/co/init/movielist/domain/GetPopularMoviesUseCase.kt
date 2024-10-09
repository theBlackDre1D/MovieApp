package co.init.movielist.domain

import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieListRepository
) {

    operator fun invoke() = repository.movies
}