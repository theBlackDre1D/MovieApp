package co.init.moviedetail.di

import co.init.database.domain.MovieLocalDataSource
import co.init.moviedetail.domain.MovieDetailRepository
import co.init.shared.IToggleFavoriteMovieStatus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideMovieDetailRepository(
        movieLocalDataSource: MovieLocalDataSource
    ): IToggleFavoriteMovieStatus {
        return MovieDetailRepository(movieLocalDataSource)
    }
}