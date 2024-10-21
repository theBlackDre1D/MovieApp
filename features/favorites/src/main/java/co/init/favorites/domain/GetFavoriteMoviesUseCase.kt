package co.init.favorites.domain

import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {

    operator fun invoke() = repository.getFavoriteMovies()
}