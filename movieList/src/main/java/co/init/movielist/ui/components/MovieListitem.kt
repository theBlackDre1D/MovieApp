package co.init.movielist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.init.core.data.Movie
import co.init.movielist.R
import co.init.movielist.ui.MovieListVM
import co.init.network.BuildConfig
import co.init.network.NetworkModules
import coil.compose.AsyncImage

@Composable
fun MovieListItem(
    movie: Movie,
    movieListVM: MovieListVM,
    onMovieClick: (Movie) -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .clickable { onMovieClick(movie) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Icon
            val imageUrl = "${NetworkModules.BASE_URL}movie/${movie.id}/images${movie.posterPath}?api_key=${BuildConfig.API_KEY}"
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_error),
                placeholder = painterResource(id = R.drawable.ic_photo_placeholder)
            )

            // Movie title
            Text(
                text = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
        }

        // Favorite
        Image(
            painter = painterResource(if (movie.isFavorite) R.drawable.ic_favorite else R.drawable.ic_not_favorite),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
        )
    }
}