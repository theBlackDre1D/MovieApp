package co.init.movielist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ErrorItem(message: String, onRetry: () -> Unit) {
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = message
        )
        Button(
            onClick = onRetry
        ) {
            Text("Retry")
        }
    }
}