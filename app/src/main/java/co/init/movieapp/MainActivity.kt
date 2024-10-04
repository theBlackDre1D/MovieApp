package co.init.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.init.info.InfoScreen
import co.init.movieapp.components.MovieAppBottomNavigationBar
import co.init.movieapp.navigation.HomeNavigationScreen
import co.init.movieapp.ui.theme.MovieAppTheme
import co.init.moviedetail.ui.MovieDetailActivity
import co.init.movielist.ui.MovieListScreen
import co.init.settings.SettingsScreen
import co.init.settings.ThemeSettings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkThemeByDefault = isSystemInDarkTheme()
            var appDarkTheme by rememberSaveable { mutableStateOf(isDarkThemeByDefault) }
            MovieAppTheme(darkTheme = appDarkTheme) {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { MovieAppBottomNavigationBar(navController) }
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = HomeNavigationScreen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(HomeNavigationScreen.Home.route) {
                            MovieListScreen() { movie ->
                                MovieDetailActivity.startActivity(this@MainActivity, movie)
                            }
                        }

                        composable(HomeNavigationScreen.Settings.route) {
                            SettingsScreen(appDarkTheme) { newThemeSettings ->
                                appDarkTheme = when (newThemeSettings) {
                                    ThemeSettings.SYSTEM -> isDarkThemeByDefault
                                    ThemeSettings.DARK -> true
                                    ThemeSettings.LIGHT -> false
                                }
                            }
                        }

                        composable(HomeNavigationScreen.Info.route) { InfoScreen() }
                    }
                }
            }
        }
    }
}