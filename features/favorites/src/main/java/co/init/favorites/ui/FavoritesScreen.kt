package co.init.favorites.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import co.init.core.R
import co.init.core.data.Movie
import co.init.core.data.TopBarConfiguration
import co.init.favorites.ui.components.MovieListItem

@Composable
fun FavoritesScreen(
    openMovieDetail: (Movie) -> Unit,
    onActionBarConfiguration: (TopBarConfiguration) -> Unit
) {
    onActionBarConfiguration(
        TopBarConfiguration(
            title = R.string.favorites_screen_name
        )
    )

    val viewModel: FavoritesScreenVM = hiltViewModel()

    val movies = viewModel.favoriteMovies.collectAsLazyPagingItems()

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
    }
}