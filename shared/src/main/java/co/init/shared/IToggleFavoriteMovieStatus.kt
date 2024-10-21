package co.init.shared

import co.init.core.data.Movie
import kotlinx.coroutines.flow.Flow

interface IToggleFavoriteMovieStatus {
    fun toggleMovieFavoriteStatus(currentFavoriteStatus: Boolean, movie: Movie): Flow<Result<Unit>>
}