package co.init.core.data

import androidx.annotation.StringRes
import co.init.core.R
import java.io.Serializable

data class TopBarConfiguration(
    @StringRes val title: Int = R.string.app_name,
    val showBackButton: Boolean = false
) : Serializable