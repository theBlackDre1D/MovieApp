package co.init.database.domain

import kotlinx.coroutines.flow.Flow

interface IIsFavoriteMovie {

    fun isFavoriteMovie(movieId: Int): Flow<Boolean>
}