package co.init.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun setUseDarkTheme(useDarkTheme: Boolean) = dataStore.set(PreferencesKeys.DARK_THEME_ENABLED, useDarkTheme)
    fun getUseDarkTheme() = dataStore.get(PreferencesKeys.DARK_THEME_ENABLED, true)

    suspend fun setSystemDefaultThemeEnabled(systemDefaultThemeEnabled: Boolean) = dataStore.set(PreferencesKeys.SYSTEM_DEFAULT_THEME, systemDefaultThemeEnabled)
    fun getSystemDefaultThemeEnabled() = dataStore.get(PreferencesKeys.SYSTEM_DEFAULT_THEME, true)
}

// Extensions
private suspend fun <T>DataStore<Preferences>.set(key: Preferences.Key<T>, newValue: T) {
    this.edit { prefs ->
        prefs[key] = newValue
    }
}

private fun <T>DataStore<Preferences>.get(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
    return this.data.map { prefs ->
        prefs[key] ?: defaultValue
    }
}