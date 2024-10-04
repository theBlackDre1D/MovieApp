package co.init.movielist.domain

import androidx.paging.map
import co.init.database.EntityMapper
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val movieListRepository: MovieListRepository
) {

    operator fun invoke() = movieListRepository.getAllFavoriteMovies().map { pagingData ->
        pagingData.map {
            EntityMapper.toMovie(it)
        }
    }
}