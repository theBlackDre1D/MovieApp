package co.init.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseVM : ViewModel() {

    protected val _error = MutableSharedFlow<String?>()
    val error get() = _error.asSharedFlow()

    protected suspend fun <R, T> wrapResult(
        result: Result<T>,
        onSuccess: (value: T) -> R,
    ) {
        result.fold(
            onSuccess = { onSuccess(it) },
            onFailure = { throwable ->
            _error.emit(throwable.message)
        })
    }
}