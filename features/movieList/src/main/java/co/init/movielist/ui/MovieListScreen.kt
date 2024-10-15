package co.init.movielist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import co.init.core.data.Movie
import co.init.movielist.R
import co.init.movielist.ui.components.ErrorItem
import co.init.movielist.ui.components.LoadingItem
import co.init.movielist.ui.components.MovieListItem

@Composable
fun MovieListScreen(
    viewModel: MovieListVM = hiltViewModel(),
    openMovieDetail: (Movie) -> Unit
) {
    val remoteMovies = viewModel.popularMovies.collectAsLazyPagingItems()
    val refreshMoviesState by remember { derivedStateOf { remoteMovies.loadState.refresh } }
    val appendMoviesState by remember { derivedStateOf { remoteMovies.loadState.append } }
    val listState = rememberLazyListState()

    val lifecycleOwner = LocalLifecycleOwner.current
    val observer = remember {
        LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                if (remoteMovies.itemCount > 0) {
                    remoteMovies.refresh()
                }
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column {
        LazyColumn(
            state = listState,
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
                        onMovieClick = { openMovieDetail(it) }
                    )
                }
            }

            // TODO try to do it better
            when (refreshMoviesState ) {
                is LoadState.Loading -> item { LoadingItem() }
                is LoadState.Error -> {
                    item {
                        val error = appendMoviesState as LoadState.Error
                        ErrorItem(
                            message = error.error.localizedMessage ?: stringResource(R.string.common_error_text),
                            onRetry = { remoteMovies.retry() }
                        )
                    }
                }
                else -> {}
            }

            when (appendMoviesState) {
                is LoadState.Loading -> item { LoadingItem() }
                is LoadState.Error -> {
                    item {
                        val error = appendMoviesState as LoadState.Error
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