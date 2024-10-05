package co.init.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferencesKeys {
    val SYSTEM_DEFAULT_THEME = booleanPreferencesKey("SYSTEM_DEFAULT_THEME")
    val DARK_THEME_ENABLED = booleanPreferencesKey("DARK_THEME_ENABLED")
}