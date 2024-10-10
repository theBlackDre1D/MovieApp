package co.init.movielist.domain

import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieListRepository
) {

    operator fun invoke() = repository.getMovies()
}