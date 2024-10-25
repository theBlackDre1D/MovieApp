package co.init.favorites.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import co.init.core.base.BaseVM
import co.init.favorites.domain.GetFavoriteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesScreenVM @Inject constructor(
    getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
) : BaseVM() {

    val favoriteMovies = getFavoriteMoviesUseCase().cachedIn(viewModelScope)
}