package co.init.moviedetail.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MovieDetailScreen() {
    val viewModel: MovieDetailVM = hiltViewModel()

    Text("Movie detail screeeeeeen")
}