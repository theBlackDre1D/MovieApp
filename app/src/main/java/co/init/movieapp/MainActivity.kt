package co.init.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.init.info.InfoScreen
import co.init.movieapp.components.MovieAppBottomNavigationBar
import co.init.movieapp.navigation.BottomNavigationScreen
import co.init.movieapp.ui.theme.MovieAppTheme
import co.init.moviedetail.ui.MovieDetailActivity
import co.init.movielist.ui.MovieListScreen
import co.init.settings.SettingsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val useSystemDefaultTheme by viewModel.useSystemDefaultTheme.collectAsState(true)
            val useDarkTheme by viewModel.useDarkTheme.collectAsState(true)
            MovieAppTheme(
                darkTheme = if (useSystemDefaultTheme) true else useDarkTheme
            ) {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { MovieAppBottomNavigationBar(navController) }
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = BottomNavigationScreen.Bottom.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(BottomNavigationScreen.Bottom.route) {
                            MovieListScreen { movie ->
                                MovieDetailActivity.startActivity(this@MainActivity, movie)
                            }
                        }

                        composable(BottomNavigationScreen.Settings.route) { SettingsScreen() }
                        composable(BottomNavigationScreen.Info.route) { InfoScreen() }
                    }
                }
            }
        }
    }
}