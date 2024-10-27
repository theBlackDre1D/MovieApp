package co.init.database

import androidx.paging.PagingData
import co.init.database.data.MovieEntity
import co.init.database.domain.MovieDao
import co.init.database.domain.MovieLocalDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MovieLocalDataSourceTest {

    lateinit var movieDao: MovieDao
    lateinit var localDataSource: MovieLocalDataSource

    @Before
    fun setup() {
        movieDao = mockk(relaxed = true)
        localDataSource = MovieLocalDataSource(movieDao)
    }

    @Test
    fun `test get favorite movies ids`() = runTest{
        coEvery { localDataSource.getAllFavoriteMoviesIds() } returns listOf(1,2,3)

        assertEquals( listOf(1,2,3), localDataSource.getAllFavoriteMoviesIds())
    }

    @Test
    fun `test get favorite movies`() = runTest {
        val movieEntity = MovieEntity(
            id = 1,
            adult = false,
            originalLanguage = "en",
            originalTitle = "Original Title",
            overview = "This is an overview of the movie.",
            popularity = 100.0,
            posterPath = "/poster.jpg",
            releaseDate = "2023-10-01",
            title = "Movie Title",
            video = false,
            voteAverage = 8.5,
            voteCount = 2000,
            isFavorite = true
        )

        val expectedMovies = listOf(movieEntity)
        val pagingData = PagingData.from(expectedMovies)

        coEvery { localDataSource.getFavoriteMovies() } returns flowOf(pagingData)

        val result = localDataSource.getFavoriteMovies().toList()
        val expected = flowOf(pagingData).toList()
        assertEquals(expected, result)
    }
}