package co.init.network.interceptors

import co.init.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val urlWithApiKey = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY) // Lepsie by to bolo ukladat v keystore a nepribalovat to v kode
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(newRequest)
    }
}