package co.init.movieapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import co.init.core.data.Movie
import co.init.core.navigation.HomeNavigation
import co.init.favorites.navigation.FavoriteListNavigation
import co.init.favorites.ui.FavoritesScreen
import co.init.moviedetail.ui.MovieDetailScreen
import co.init.movielist.navigation.MovieListNavigation
import co.init.movielist.ui.MovieListScreen

fun NavGraphBuilder.addMovieListNavGraph(navController: NavController) {
    navigation<HomeNavigation.Home>(startDestination = MovieListNavigation.MovieList) {
        composable<MovieListNavigation.MovieList> {
            MovieListScreen(
                openMovieDetail = { navController.navigate(it) }
            )
        }

        movieDetail()
    }
}

fun NavGraphBuilder.addFavoritesNavGraph(navController: NavController) {
    navigation<HomeNavigation.Favorites>(startDestination = FavoriteListNavigation.FavoriteList) {
        composable<FavoriteListNavigation.FavoriteList> {
            FavoritesScreen(
                openMovieDetail = { navController.navigate(it) }
            )
        }

        movieDetail()
    }
}

private fun NavGraphBuilder.movieDetail() {
    composable<Movie> { backstackEntry ->
        MovieDetailScreen(backstackEntry.toRoute())
    }
}