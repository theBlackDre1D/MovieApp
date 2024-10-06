package co.init.database.domain

import kotlinx.coroutines.flow.Flow

interface IHasLocalDataSource {

    fun isFavoriteMovie(movieId: Int): Flow<Boolean>
}