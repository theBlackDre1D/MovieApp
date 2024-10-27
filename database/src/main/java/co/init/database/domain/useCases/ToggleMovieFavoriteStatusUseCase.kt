package co.init.database.domain.useCases

import co.init.core.data.Movie
import co.init.database.data.EntityMapper
import co.init.database.domain.MovieLocalDataSource
import javax.inject.Inject

class ToggleMovieFavoriteStatusUseCase @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource
) {

    operator fun invoke(movie: Movie) =
        movieLocalDataSource.toggleMovieFavoriteStatus(EntityMapper.toMovieEntity(movie))
}