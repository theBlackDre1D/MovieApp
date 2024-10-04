package co.init.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.init.settings.components.SwitchWithText

@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    onThemeSettingsChanges: (ThemeSettings) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var isThemeBySystemEnabled by rememberSaveable { mutableStateOf(true) }
        var useDarkTheme by rememberSaveable { mutableStateOf(isDarkTheme) }

        SwitchWithText(
            checked = isThemeBySystemEnabled,
            text = stringResource(R.string.settings_theme_use_system_default)
        ) { isChecked ->
            isThemeBySystemEnabled = isChecked

            if (isChecked) {
                onThemeSettingsChanges(ThemeSettings.SYSTEM)
            } else {
                onThemeSettingsChanges(if (useDarkTheme) ThemeSettings.DARK else ThemeSettings.LIGHT)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SwitchWithText(
            checked = useDarkTheme,
            enabled = !isThemeBySystemEnabled,
            text = stringResource(R.string.settings_theme_use_darkMode)
        ) { isChecked ->
            useDarkTheme = isChecked

            onThemeSettingsChanges(if (isChecked) ThemeSettings.DARK else ThemeSettings.LIGHT)
        }
    }
}