package co.init.movieapp.di

import android.content.Context
import co.init.movieapp.connectivityManager.ConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Modules {

    @Provides
    fun provideConnectivityObserver(@ApplicationContext applicationContext: Context): ConnectivityObserver {
        return ConnectivityObserver(applicationContext)
    }
}