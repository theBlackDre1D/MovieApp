package co.init.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import co.init.core.data.Movie
import co.init.info.InfoScreen
import co.init.movieapp.components.MovieAppBottomNavigationBar
import co.init.movieapp.navigation.HomeNavigationScreen
import co.init.movieapp.ui.theme.MovieAppTheme
import co.init.moviedetail.ui.MovieDetailScreen
import co.init.movielist.ui.MovieListScreen
import co.init.movielist.ui.MovieListVM
import co.init.settings.SettingsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MovieListVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { MovieAppBottomNavigationBar(navController) }
                ) { innerPadding ->

                    NavHost(navController = navController, startDestination = HomeNavigationScreen.Home.route) {
                        composable(HomeNavigationScreen.Home.route) {
                            MovieListScreen(viewModel) { movie ->
                                navController.navigate(movie)
                            }
                        }
                        composable(HomeNavigationScreen.Settings.route) { SettingsScreen() }
                        composable(HomeNavigationScreen.Info.route) { InfoScreen() }

                        composable<Movie> { backStackEntry ->
                            val movie: Movie = backStackEntry.toRoute()
                            MovieDetailScreen(movie)
                        }
                    }
                }
            }
        }
    }
}