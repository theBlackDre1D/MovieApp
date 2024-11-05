package co.init.movieapp.ui.mainActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.init.core.data.TopBarConfiguration
import co.init.info.InfoScreen
import co.init.movieapp.components.bottomNavigationBar.MovieAppBottomNavigationBar
import co.init.movieapp.components.topBar.TopBar
import co.init.movieapp.navigation.HomeNavigation
import co.init.movieapp.navigation.addFavoritesNavGraph
import co.init.movieapp.navigation.addMovieListNavGraph
import co.init.movieapp.ui.theme.MovieAppTheme
import co.init.settings.SettingsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isConnectedToNetwork by viewModel.isConnectedToNetwork.collectAsState()
            val topBarConfiguration = rememberSaveable { mutableStateOf(TopBarConfiguration()) }

            val useSystemDefaultTheme by viewModel.useSystemDefaultTheme.collectAsState(true)
            val useDarkTheme by viewModel.useDarkTheme.collectAsState(true)

            MovieAppTheme(
                darkTheme = if (useSystemDefaultTheme) isSystemInDarkTheme() else useDarkTheme
            ) {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopBar(navController, isConnectedToNetwork, topBarConfiguration.value) },
                    bottomBar = { MovieAppBottomNavigationBar(navController) },
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = HomeNavigation.Home,
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        // Home
                        addMovieListNavGraph(navController) { topBarConfiguration.value = it }

                        // Favorites
                        addFavoritesNavGraph(navController) { topBarConfiguration.value = it }

                        composable<HomeNavigation.Settings> {
                            SettingsScreen { topBarConfiguration.value = it }
                        }
                        composable<HomeNavigation.Info> {
                            InfoScreen { topBarConfiguration.value = it }
                        }
                    }
                }
            }
        }
    }
}