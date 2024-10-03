package co.init.movielist.domain

import co.init.core.data.Movie
import co.init.database.EntityMapper
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) {

    suspend fun getPopularMovies() = remoteDataSource.getPopularMovies()
    suspend fun getSavedMovies() = localDataSource.getAllMovies()

    suspend fun test() {
        val remote = remoteDataSource.getPopularMovies()
        val local = localDataSource.getAllMovies()

        remote.combine(local) { remote, local ->

        }
    }

    suspend fun savePopularMoviesToDatabase(movies: List<Movie>) {
        val dbMovies = EntityMapper.fromMovies(movies)
        localDataSource.saveMovies(dbMovies)
    }
}