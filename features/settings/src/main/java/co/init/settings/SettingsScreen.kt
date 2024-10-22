package co.init.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.init.core.R
import co.init.core.data.TopBarConfiguration
import co.init.settings.components.SwitchWithText

@Composable
fun SettingsScreen(
    onTopBarConfiguration: (TopBarConfiguration) -> Unit
) {
    val viewModel: SettingsScreenVM = hiltViewModel()

    onTopBarConfiguration(
        TopBarConfiguration(
            title = R.string.bottom_navigation_settings
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val useSystemDefaultTheme by viewModel.useSystemDefaultTheme.collectAsState(true)
        val useDarkTheme by viewModel.useDarkTheme.collectAsState(true)

        // Use system default switch
        SwitchWithText(
            checked = useSystemDefaultTheme,
            text = stringResource(R.string.settings_theme_use_system_default)
        ) { isChecked ->
            viewModel.setUseSystemDefaultTheme(isChecked)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Use dark mode switch
        SwitchWithText(
            checked = useDarkTheme,
            enabled = !useSystemDefaultTheme,
            text = stringResource(R.string.settings_theme_use_darkMode)
        ) { isChecked ->
            viewModel.setUseDarkTheme(isChecked)
        }
    }
}