package co.init.movielist.domain.useCases

import androidx.paging.map
import co.init.database.EntityMapper
import co.init.movielist.domain.MovieRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    // TODO odstranit ak nebude treba nakoniec
    //    suspend operator fun invoke() = movieRepository.getSavedMovies().map { result ->
//        result.fold(
//            onSuccess = {
//                Result.success(EntityMapper.toMovies(it))
//            },
//            onFailure = {
//                Result.failure(it)
//            }
//        )
//    }
    operator fun invoke() = movieRepository.getAllFavoriteMovies().map { pagingData ->
        pagingData.map {
            EntityMapper.toMovie(it)
        }
    }
}