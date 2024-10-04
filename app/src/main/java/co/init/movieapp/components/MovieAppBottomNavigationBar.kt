package co.init.movieapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import co.init.movieapp.navigation.HomeNavigationScreen

@Composable
fun MovieAppBottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavigationScreens = listOf(
        HomeNavigationScreen.Home,
        HomeNavigationScreen.Settings,
        HomeNavigationScreen.Info
    )

    NavigationBar {
        bottomNavigationScreens.forEach { screen ->
            NavigationBarItem(
                modifier = Modifier.padding(bottom = 8.dp),
                icon = { Image(painterResource(screen.icon), null) },
                label = { Text(stringResource(screen.label)) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}