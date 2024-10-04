package co.init.movielist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ErrorItem(message: String, onRetry: () -> Unit) {
    Column(
      modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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