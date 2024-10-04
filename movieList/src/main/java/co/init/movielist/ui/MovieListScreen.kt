package co.init.movielist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import co.init.core.data.Movie
import co.init.movielist.ui.components.ErrorItem
import co.init.movielist.ui.components.MovieListItem

@Composable
fun MovieListScreen(
    viewModel: MovieListVM = hiltViewModel(),
    openMovieDetail: (Movie) -> Unit) {
    val remoteMovies = viewModel.popularMovies.collectAsLazyPagingItems()
    val localMovies = viewModel.favoriteMovies.collectAsLazyPagingItems()

    Column {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = remoteMovies.itemCount,
                key = remoteMovies.itemKey { it.id },
                contentType = remoteMovies.itemContentType()
            ) { index ->
                remoteMovies[index]?.let { movie ->
                    MovieListItem(
                        movie = movie,
                        viewModel,
                        onMovieClick = { openMovieDetail(it) }
                    )
                }
            }

            remoteMovies.apply {
                when (loadState.append) {
                    is LoadState.Loading -> {
                        item {
                            // TODO better progress
                            CircularProgressIndicator()
                        }
                    }

                    is LoadState.Error -> {
                        // TODO do it better
                        val error = remoteMovies.loadState.append as LoadState.Error
                        item {
                            ErrorItem(
                                message = error.error.localizedMessage ?: "An error occurred",
                                onRetry = { remoteMovies.retry() }
                            )
                        }

                        // Show favorites movies from DB if some
                        if (remoteMovies.itemCount == 0) {
                            items(
                                count = localMovies.itemCount,
                                key = localMovies.itemKey { it.id },
                                contentType = localMovies.itemContentType()
                            ) { index ->
                                localMovies[index]?.let { movie ->
                                    MovieListItem(
                                        movie = movie,
                                        viewModel,
                                        onMovieClick = { openMovieDetail(it) }
                                    )
                                }
                            }
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}