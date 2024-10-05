package co.init.base

import androidx.lifecycle.ViewModel
import co.init.preferences.PreferencesManager

abstract class BaseActivityVM(
    preferencesManager: PreferencesManager
) : ViewModel() {

    val useDarkTheme = preferencesManager.getUseDarkTheme()
    val useSystemDefaultTheme = preferencesManager.getSystemDefaultThemeEnabled()
}