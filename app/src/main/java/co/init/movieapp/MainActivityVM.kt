package co.init.movieapp

import co.init.base.BaseActivityVM
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