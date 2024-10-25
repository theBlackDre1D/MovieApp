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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.init.core.R
import co.init.core.data.Movie
import co.init.core.data.TopBarConfiguration
import co.init.core.extensions.safe
import co.init.core.ui.SetupErrorHandling
import coil.compose.AsyncImage

@Composable
fun MovieDetailScreen(
    movie: Movie,
    onTopBarConfiguration: (TopBarConfiguration) -> Unit
) {
    onTopBarConfiguration(
        TopBarConfiguration(R.string.movie_detail_screen_name, true)
    )

    val viewModel: MovieDetailScreenVM = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(MovieDetailScreenVM.Intent.LoadMovie(movie))
    }

    SetupErrorHandling(viewModel)

    val onIntent: (MovieDetailScreenVM.Intent) -> Unit = { intent ->
        viewModel.handleIntent(intent)
    }

    if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        MovieDetailScreenLandscape(state, onIntent)
    } else {
        MovieDetailScreenPortrait(state, onIntent)
    }
}

@Composable
private fun MovieDetailScreenPortrait(
    state: MovieDetailScreenVM.State,
    sendIntent: (MovieDetailScreenVM.Intent) -> Unit)
{
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
            model = state.movie?.imageUrl,
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
                text = state.movie?.title.orEmpty(),
                fontSize = 24.sp
            )

            // Favorite
            val imageResource = if (state.movie?.isFavorite.safe()) R.drawable.ic_favorite else R.drawable.ic_not_favorite
            Image(
                painter = painterResource(imageResource),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(top = 16.dp)
                    .clickable { sendIntent(MovieDetailScreenVM.Intent.LikeMovie) }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(R.string.movie_detail_popularity, state.movie?.voteAverage.toString()),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = state.movie?.overview.orEmpty(),
            fontSize = 16.sp
        )
    }
}

@Composable
private fun MovieDetailScreenLandscape(
    state: MovieDetailScreenVM.State,
    sendIntent: (MovieDetailScreenVM.Intent) -> Unit
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Icon
        AsyncImage(
            model = state.movie?.imageUrl,
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
                    text = state.movie?.title.orEmpty(),
                    fontSize = 24.sp
                )

                // Favorite
                val imageResource = if (state.movie?.isFavorite.safe()) R.drawable.ic_favorite else R.drawable.ic_not_favorite
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(top = 16.dp)
                        .clickable {
                            sendIntent(MovieDetailScreenVM.Intent.LikeMovie)
                        }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.movie_detail_popularity, state.movie?.voteAverage.toString()),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = state.movie?.overview.orEmpty(),
                fontSize = 16.sp
            )
        }
    }
}