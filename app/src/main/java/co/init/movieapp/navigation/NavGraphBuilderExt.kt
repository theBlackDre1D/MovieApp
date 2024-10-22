package co.init.movieapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import co.init.core.data.Movie
import co.init.core.data.TopBarConfiguration
import co.init.favorites.navigation.FavoriteListNavigation
import co.init.favorites.ui.FavoritesScreen
import co.init.moviedetail.ui.MovieDetailScreen
import co.init.movielist.navigation.MovieListNavigation
import co.init.movielist.ui.MovieListScreen

fun NavGraphBuilder.addMovieListNavGraph(
    navController: NavController,
    onTopBarConfiguration: (TopBarConfiguration) -> Unit
) {
    navigation<HomeNavigation.Home>(startDestination = MovieListNavigation.MovieList) {
        composable<MovieListNavigation.MovieList> {
            MovieListScreen(
                openMovieDetail = { navController.navigate(it) },
                onTopBarConfiguration = onTopBarConfiguration
            )
        }

        movieDetail(onTopBarConfiguration)
    }
}

fun NavGraphBuilder.addFavoritesNavGraph(
    navController: NavController,
    onTopBarConfiguration: (TopBarConfiguration) -> Unit
) {
    navigation<HomeNavigation.Favorites>(startDestination = FavoriteListNavigation.FavoriteList) {
        composable<FavoriteListNavigation.FavoriteList> {
            FavoritesScreen(
                openMovieDetail = { navController.navigate(it) },
                onActionBarConfiguration = onTopBarConfiguration
            )
        }

        movieDetail(onTopBarConfiguration)
    }
}

private fun NavGraphBuilder.movieDetail(onTopBarConfiguration: (TopBarConfiguration) -> Unit) {
    composable<Movie> { backstackEntry ->
        MovieDetailScreen(backstackEntry.toRoute(), onTopBarConfiguration)
    }
}