package co.init.core.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class BaseRemoteSource {

    suspend fun <T>executeCall(apiCall: suspend () -> Response<T>): Flow<Result<T>> {
        return flow {
            val response = apiCall()
            if (response.isSuccessful) {
                emit(Result.success(response.body()!!))
            } else {
                emit(Result.failure(Throwable(response.message())))
            }
        }.catch {
            emit(Result.failure(it))
        }
    }
}