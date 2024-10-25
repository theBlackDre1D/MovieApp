package co.init.favorites.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import co.init.core.R
import co.init.core.components.ErrorItem
import co.init.core.components.LoadingItem
import co.init.core.components.movieListItem.MovieListItem
import co.init.core.data.Movie
import co.init.core.data.TopBarConfiguration

@Composable
fun FavoritesScreen(
    openMovieDetail: (Movie) -> Unit,
    onTopBarConfiguration: (TopBarConfiguration) -> Unit
) {
    onTopBarConfiguration(
        TopBarConfiguration(
            title = R.string.favorites_screen_name
        )
    )

    val viewModel: FavoritesScreenVM = hiltViewModel()

    val movies = viewModel.favoriteMovies.collectAsLazyPagingItems()
    val refreshMoviesState by remember { derivedStateOf { movies.loadState.refresh } }
    val appendMoviesState by remember { derivedStateOf { movies.loadState.append } }

    LazyColumn {
        items(
            count = movies.itemCount,
            contentType = movies.itemContentType()
        ) { index ->
            movies[index]?.let { movie ->
                MovieListItem(movie) {
                    openMovieDetail(it)
                }
            }
        }

        when (refreshMoviesState ) {
            is LoadState.Loading -> item { LoadingItem() }
            is LoadState.Error -> {
                item {
                    val error = appendMoviesState as LoadState.Error
                    ErrorItem(
                        message = error.error.localizedMessage ?: stringResource(R.string.common_error_text),
                        onRetry = { movies.retry() }
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
                        onRetry = { movies.retry() }
                    )
                }
            }
            else -> {}
        }
    }
}