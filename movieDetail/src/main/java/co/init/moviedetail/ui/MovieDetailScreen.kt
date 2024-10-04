package co.init.moviedetail.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import co.init.core.data.Movie

@Composable
fun MovieDetailScreen(
    movie: Movie
) {
    val viewModel: MovieDetailVM = hiltViewModel()

    Text("Movie detail screeeeeeen: ${movie.title}")
}