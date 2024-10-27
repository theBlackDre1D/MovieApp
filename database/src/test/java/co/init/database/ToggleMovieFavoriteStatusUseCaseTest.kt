package co.init.database

import co.init.core.data.Movie
import co.init.database.domain.MovieLocalDataSource
import co.init.database.domain.useCases.ToggleMovieFavoriteStatusUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ToggleMovieFavoriteStatusUseCaseTest {

    private lateinit var movieLocalDataSource: MovieLocalDataSource
    lateinit var toggleMovieFavoriteStatusUseCase: ToggleMovieFavoriteStatusUseCase

    private val movie = Movie(
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

    @Before
    fun setup() {
        movieLocalDataSource = mockk(relaxed = true)
        toggleMovieFavoriteStatusUseCase = ToggleMovieFavoriteStatusUseCase(movieLocalDataSource)
    }

    @Test
    fun `test if use case emits value as should`() = runTest {
        val expectedFlow = flowOf(Result.success(Unit))

        coEvery { toggleMovieFavoriteStatusUseCase(movie) } returns expectedFlow

        val result = toggleMovieFavoriteStatusUseCase(movie).toList()
        assertEquals(expectedFlow.toList(), result)
    }
}