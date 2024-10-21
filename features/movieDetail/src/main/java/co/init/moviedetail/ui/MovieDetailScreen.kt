package co.init.moviedetail.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.init.core.R
import co.init.core.data.Movie
import coil.compose.AsyncImage

@Composable
fun MovieDetailScreen(movie: Movie) {
    if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        MovieDetailScreenLandscape(movie)
    } else {
        MovieDetailScreenPortrait(movie)
    }
}

@Composable
private fun MovieDetailScreenPortrait(movie: Movie) {
    val viewModel: MovieDetailScreenVM = hiltViewModel()
    viewModel.currentMovie = movie

    val isFavorite by viewModel.isFavoriteFlow.collectAsStateWithLifecycle(initialValue = movie.isFavorite)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Icon
        AsyncImage(
            model = movie.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth() ,
            contentScale = ContentScale.Fit,
            error = painterResource(id = R.drawable.ic_error),
            placeholder = painterResource(id = R.drawable.ic_photo_placeholder)
        )

        Row {
            Text(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .weight(1f),
                text = movie.title,
                fontSize = 24.sp
            )

            // Favorite
            val imageResource = if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_not_favorite
            Image(
                painter = painterResource(imageResource),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(top = 16.dp)
                    .clickable { viewModel.onFavoriteIconClick(isFavorite, movie) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = movie.overview,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun MovieDetailScreenLandscape(movie: Movie) {
    val viewModel: MovieDetailScreenVM = hiltViewModel()
    viewModel.currentMovie = movie

    val isFavorite by viewModel.isFavoriteFlow.collectAsStateWithLifecycle(initialValue = movie.isFavorite)
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Icon
        AsyncImage(
            model = movie.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(400.dp)
                .fillMaxWidth() ,
            contentScale = ContentScale.Fit,
            error = painterResource(id = R.drawable.ic_error),
            placeholder = painterResource(id = R.drawable.ic_photo_placeholder)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column (
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .verticalScroll(scrollState),
        ) {

            Row {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .weight(1f),
                    text = movie.title,
                    fontSize = 24.sp
                )

                // Favorite
                val imageResource = if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_not_favorite
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(top = 16.dp)
                        .clickable { viewModel.onFavoriteIconClick(isFavorite, movie) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = movie.overview,
                fontSize = 16.sp
            )
        }
    }
}