package co.init.movieapp.components

import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import co.init.favorites.navigation.FavoriteListNavigation
import co.init.movieapp.navigation.HomeNavigationItems
import co.init.movielist.navigation.MovieListNavigation

@Composable
fun MovieAppBottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val homeNavigationItems = listOf(
        HomeNavigationItems.Home,
        HomeNavigationItems.Favorites,
        HomeNavigationItems.Settings,
        HomeNavigationItems.Info
    )

    if (showBottomNavigationBar(currentRoute)) {
        NavigationBar {
            homeNavigationItems.forEach { screen ->
                NavigationBarItem(
                    icon = { Image(painterResource(screen.icon), null) },
                    label = { Text(stringResource(screen.label)) },
                    selected = currentRoute == screen.destination,
                    onClick = {
                        navController.navigate(screen.destination) {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

// TODO try make it better
fun showBottomNavigationBar(currentRoute: String?): Boolean {
    return currentRoute == HomeNavigationItems.Home.destination::class.qualifiedName ||
            currentRoute == HomeNavigationItems.Favorites.destination::class.qualifiedName ||
            currentRoute == HomeNavigationItems.Settings.destination::class.qualifiedName ||
            currentRoute == HomeNavigationItems.Info.destination::class.qualifiedName ||
            currentRoute == MovieListNavigation.MovieList::class.qualifiedName ||
            currentRoute == FavoriteListNavigation.FavoriteList::class.qualifiedName
}