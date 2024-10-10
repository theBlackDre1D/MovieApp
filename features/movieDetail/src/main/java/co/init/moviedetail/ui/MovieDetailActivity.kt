package co.init.moviedetail.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import co.init.core.data.Movie
import co.init.moviedetail.ui.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : ComponentActivity() {

    private val viewModel: MovieDetailActivityVM by viewModels()

    companion object {

        private const val MOVIE_BUNDLE_KEY = "MOVIE_BUNDLE_KEY"

        fun startActivity(context: Context, movie: Movie) {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_BUNDLE_KEY, movie)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(MOVIE_BUNDLE_KEY, Movie::class.java)
        } else {
            intent.getSerializableExtra(MOVIE_BUNDLE_KEY) as? Movie
        }
        if (movie == null) finishActivity(RESULT_CANCELED)

        setContent {
            val useSystemDefaultTheme by viewModel.useSystemDefaultTheme.collectAsState(true)
            val useDarkTheme by viewModel.useDarkTheme.collectAsState(true)
            MovieAppTheme(
                darkTheme = if (useSystemDefaultTheme) isSystemInDarkTheme() else useDarkTheme
            ) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    movie?.let {
                        MovieDetailScreen(it)
                    }
                }
            }
        }
    }
}