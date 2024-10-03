package co.init.movielist.domain

import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val saveMoviesToDatabaseUseCase: SaveMoviesToDatabaseUseCase
) {

    suspend operator fun invoke() = repository.getPopularMovies().map { result ->
        result.onSuccess { success ->
            saveMoviesToDatabaseUseCase(success.results)
        }
    }
}