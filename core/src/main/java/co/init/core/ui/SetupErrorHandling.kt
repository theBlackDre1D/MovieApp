package co.init.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import co.init.core.base.BaseVM
import co.init.core.components.dialogs.Dialogs

@Composable
fun SetupErrorHandling(viewModel: BaseVM) {
    var errorMessage by rememberSaveable { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.error.collect { error ->
            errorMessage = error
        }
    }

    errorMessage?.let { message ->
        Dialogs.ShowErrorDialog(message) { errorMessage = null }
    }
}