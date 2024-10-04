package co.init.database.domain

import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val localDataSource: MovieLocalDataSource
) {

    suspend fun isFavoriteMovie(movieId: Int) = localDataSource.isFavoriteMovie(movieId)
}