package co.init.database.di

import android.content.Context
import androidx.room.Room
import co.init.database.domain.MovieDao
import co.init.database.domain.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModules {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext appContext: Context): MovieDatabase {
        return Room.databaseBuilder(appContext, MovieDatabase::class.java, "movie_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: MovieDatabase): MovieDao {
        return appDatabase.movieDao()
    }
}