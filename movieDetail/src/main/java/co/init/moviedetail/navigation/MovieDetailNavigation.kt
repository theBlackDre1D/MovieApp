package co.init.moviedetail.navigation

import co.init.core.navigation.Screen

sealed class MovieDetailNavigation(override val route: String) : Screen(route) {
    data object MovieDetail : MovieDetailNavigation("movieDetail")
}