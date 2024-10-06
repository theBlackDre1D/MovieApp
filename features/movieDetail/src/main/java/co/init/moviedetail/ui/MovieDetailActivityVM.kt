package co.init.moviedetail.ui

import co.init.base.BaseActivityVM
import co.init.preferences.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailActivityVM @Inject constructor(
    preferencesManager: PreferencesManager
) : BaseActivityVM(preferencesManager)