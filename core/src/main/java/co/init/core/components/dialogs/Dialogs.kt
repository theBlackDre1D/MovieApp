package co.init.core.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import co.init.core.R

object Dialogs {

    @Composable
    fun ShowOkDialog(message: String, title: String, onDismiss: () -> Unit) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = title) },
            text = { Text(text = message) },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(R.string.common_ok))
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }

    @Composable
    fun ShowErrorDialog(message: String, onDismiss: () -> Unit) {
        ShowOkDialog(message, title = stringResource(R.string.common_error), onDismiss)
    }
}