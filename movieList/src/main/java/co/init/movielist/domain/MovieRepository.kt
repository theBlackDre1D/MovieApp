package co.init.movielist.domain

import co.init.core.data.Movie
import co.init.database.MovieEntity
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) {

    suspend fun getPopularMovies() = remoteDataSource.getPopularMovies()
    suspend fun savePopularMoviesToDatabase(movies: List<Movie>) {
        val dbMovies = MovieEntity.fromMovies(movies)
        localDataSource.saveMovies(dbMovies)
    }
}