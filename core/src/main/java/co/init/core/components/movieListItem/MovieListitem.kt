package co.init.core.components.movieListItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.init.core.R
import co.init.core.data.Movie
import coil.compose.AsyncImage

@Composable
fun MovieListItem(
    movie: Movie,
    onMovieClick: (Movie) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onMovieClick(movie) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Icon
            AsyncImage(
                model = movie.thumbnailUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_error),
                placeholder = painterResource(id = R.drawable.ic_photo_placeholder)
            )

            // Movie title
            Text(
                text = movie.title,
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Favorite
                val imageResource = if (movie.isFavorite) R.drawable.ic_favorite else R.drawable.ic_not_favorite
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
        )
    }
}