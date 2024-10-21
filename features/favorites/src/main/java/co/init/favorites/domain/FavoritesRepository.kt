package co.init.favorites.domain

import androidx.paging.map
import co.init.database.data.EntityMapper
import co.init.database.domain.MovieLocalDataSource
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepository @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource
) {

    fun getFavoriteMovies() = movieLocalDataSource.getFavoriteMovies().map {
        it.map { movieEntity ->
            EntityMapper.toMovie(movieEntity)
        }
    }
}