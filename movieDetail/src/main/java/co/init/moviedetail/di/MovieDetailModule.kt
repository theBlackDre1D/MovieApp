package co.init.moviedetail.di

import co.init.database.domain.IHasLocalDataSource
import co.init.database.domain.MovieLocalDataSource
import co.init.moviedetail.domain.MovieDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MovieDetailModule {
    
    @Provides
    fun provideRepoWithLocalDataSource(localDataSource: MovieLocalDataSource): IHasLocalDataSource {
        return MovieDetailRepository(localDataSource)
    }
}