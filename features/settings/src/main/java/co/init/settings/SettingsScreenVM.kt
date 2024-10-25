package co.init.settings

import co.init.core.base.BaseVM
import co.init.core.extensions.doInCoroutine
import co.init.preferences.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsScreenVM @Inject constructor(
    private val preferencesManager: PreferencesManager
) : BaseVM() {

    val useDarkTheme = preferencesManager.getUseDarkTheme()
    val useSystemDefaultTheme = preferencesManager.getSystemDefaultThemeEnabled()

    fun setUseDarkTheme(useDarkTheme: Boolean) = doInCoroutine { preferencesManager.setUseDarkTheme(useDarkTheme) }
    fun setUseSystemDefaultTheme(useSystemDefaultTheme: Boolean) = doInCoroutine { preferencesManager.setSystemDefaultThemeEnabled(useSystemDefaultTheme) }
}