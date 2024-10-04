package co.init.movielist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import co.init.core.data.Movie
import co.init.movielist.R
import co.init.movielist.ui.components.ErrorItem
import co.init.movielist.ui.components.MovieListItem

@Composable
fun MovieListScreen(
    viewModel: MovieListVM = hiltViewModel(),
    openMovieDetail: (Movie) -> Unit
) {
    val remoteMovies = viewModel.popularMovies.collectAsLazyPagingItems()
    val fetchingMoviesState by remember { derivedStateOf { remoteMovies.loadState.refresh } }

    Column {
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = remoteMovies.itemCount,
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

            when (fetchingMoviesState) {
                is LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }

                is LoadState.Error -> {
                    val error = fetchingMoviesState as LoadState.Error
                    item {
                        ErrorItem(
                            message = error.error.localizedMessage ?: stringResource(R.string.common_error_text),
                            onRetry = { remoteMovies.retry() }
                        )
                    }
                }
                else -> {}
            }
        }
    }
}