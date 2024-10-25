package co.init.core.base

import co.init.preferences.PreferencesManager

abstract class BaseActivityVM(
    preferencesManager: PreferencesManager
) : BaseVM() {

    val useDarkTheme = preferencesManager.getUseDarkTheme()
    val useSystemDefaultTheme = preferencesManager.getSystemDefaultThemeEnabled()
}