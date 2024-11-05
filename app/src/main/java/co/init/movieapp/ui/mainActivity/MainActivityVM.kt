package co.init.movieapp.ui.mainActivity

import co.init.core.base.BaseActivityVM
import co.init.movieapp.connectivityManager.ConnectivityObserver
import co.init.preferences.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    preferencesManager: PreferencesManager,
    connectivityObserver: ConnectivityObserver
) : BaseActivityVM(preferencesManager) {

    val isConnectedToNetwork = connectivityObserver.isConnected
}