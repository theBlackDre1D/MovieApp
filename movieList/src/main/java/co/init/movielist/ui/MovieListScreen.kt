package co.init.movielist.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun MovieListScreen() {

    val viewModel: MovieListVM = hiltViewModel()

    Text(viewModel.text)
}