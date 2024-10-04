package co.init.moviedetail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import co.init.core.data.Movie
import co.init.moviedetail.ui.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : ComponentActivity() {

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

        val movie = intent.getSerializableExtra(MOVIE_BUNDLE_KEY, Movie::class.java)
        if (movie == null) finishActivity(RESULT_CANCELED)

        setContent {
            MovieAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    movie?.let {
                        MovieDetailScreen(it)
                    }
                }
            }
        }
    }
}