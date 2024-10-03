package co.init.movielist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.init.movielist.ui.components.MovieListItem


@Composable
fun MovieListScreen(viewModel: MovieListVM) {
    val state = viewModel.movies.collectAsStateWithLifecycle()

    Column {
        if (state.value.loading) { // TODO later add loading to list
            CircularProgressIndicator()
        }

        Text(viewModel.text)

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items(state.value.movies) {movie ->
                MovieListItem(movie)
            }
        }
    }
}