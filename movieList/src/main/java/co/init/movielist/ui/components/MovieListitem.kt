package co.init.movielist.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import co.init.core.data.Movie

@Composable
fun MovieListItem(movie: Movie) {
    Text(text = movie.title)
}