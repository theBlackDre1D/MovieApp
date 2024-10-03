package co.init.movielist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import co.init.movielist.ui.components.ErrorItem
import co.init.movielist.ui.components.MovieListItem

@Composable
fun MovieListScreen(viewModel: MovieListVM) {
    val movies = viewModel.popularMovies.collectAsLazyPagingItems()

    Column {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = movies.itemCount,
                key = movies.itemKey { it.id },
                contentType = movies.itemContentType()
            ) { index ->
                val movie = movies[index]
                movie?.let {
                    MovieListItem(it)
                }
            }

            movies.apply {
                when (loadState.append) {
                    is LoadState.Loading -> {
                        item {
                            // TODO better progress
                            CircularProgressIndicator()
                        }
                    }

                    is LoadState.Error -> {
                        // TODO do it better
                        val error = movies.loadState.append as LoadState.Error
                        item {
                            ErrorItem(
                                message = error.error.localizedMessage ?: "An error occurred",
                                onRetry = { movies.retry() }
                            )
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}