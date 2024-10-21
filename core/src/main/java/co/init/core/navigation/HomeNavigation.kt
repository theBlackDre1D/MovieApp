package co.init.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeNavigation {
    @Serializable object Home
    @Serializable object Favorites
    @Serializable object Settings
    @Serializable object Info
}