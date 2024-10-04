package co.init.movieapp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import co.init.core.navigation.Screen
import co.init.movieapp.R

sealed class HomeNavigationScreen(
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
    override val route: String
) : Screen(route) {
    data object Home : HomeNavigationScreen(R.drawable.ic_home, R.string.bottom_navigation_home, "home")
    data object Settings : HomeNavigationScreen(R.drawable.ic_settings, R.string.bottom_navigation_settings, "settings")
    data object Info : HomeNavigationScreen(R.drawable.ic_info, R.string.bottom_navigation_info, "info")
}