package co.init.moviedetail.domain

import co.init.core.data.Movie
import co.init.database.data.EntityMapper
import co.init.database.domain.MovieLocalDataSource
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(
    private val localDataSource: MovieLocalDataSource
) {

    fun toggleMovieFavoriteStatus(currentFavoriteStatus: Boolean, movie: Movie) =
        localDataSource.toggleMovieFavoriteStatus(currentFavoriteStatus, EntityMapper.toMovieEntity(movie))
}