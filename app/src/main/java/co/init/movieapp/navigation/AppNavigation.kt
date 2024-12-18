package co.init.movieapp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import co.init.core.R
import kotlinx.serialization.Serializable

sealed class HomeNavigationItems(
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
    val destination: Any
) {
    data object Home : HomeNavigationItems(R.drawable.ic_home, R.string.bottom_navigation_home, HomeNavigation.Home)
    data object Favorites : HomeNavigationItems(R.drawable.ic_favorite, R.string.bottom_navigation_favorites, HomeNavigation.Favorites)
    data object Settings : HomeNavigationItems(R.drawable.ic_settings, R.string.bottom_navigation_settings, HomeNavigation.Settings)
    data object Info : HomeNavigationItems(R.drawable.ic_info, R.string.bottom_navigation_info, HomeNavigation.Info)
}

@Serializable
object HomeNavigation {
    @Serializable object Home
    @Serializable object Favorites
    @Serializable object Settings
    @Serializable object Info
}