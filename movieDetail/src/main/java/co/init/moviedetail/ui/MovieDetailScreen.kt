package co.init.moviedetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.init.core.data.Movie
import co.init.moviedetail.R
import co.init.network.BuildConfig
import co.init.network.NetworkModules
import coil.compose.AsyncImage

@Composable
fun MovieDetailScreen(
    movie: Movie
) {
    val viewModel: MovieDetailVM = hiltViewModel()

    val isFavorite = viewModel.isFavoriteFlow.collectAsStateWithLifecycle(false)

    LaunchedEffect(isFavorite) {
        viewModel.checkIsFavorite()
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Favorite
        val imageResource = if (isFavorite.value) R.drawable.ic_favorite else R.drawable.ic_not_favorite
        Image(
            painter = painterResource(imageResource),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clickable { viewModel.onFavoriteIconClick(isFavorite.value) }
        )

        // Icon
        val imageUrl = "${NetworkModules.BASE_URL}movie/${movie.id}/images${movie.posterPath}?api_key=${BuildConfig.API_KEY}"
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .padding(16.dp),
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.ic_error),
            placeholder = painterResource(id = R.drawable.ic_photo_placeholder)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = movie.title,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = movie.overview,
            fontSize = 16.sp
        )
    }

}