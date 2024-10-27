package co.init.favorites

import androidx.paging.PagingData
import co.init.core.data.Movie
import co.init.database.domain.MovieLocalDataSource
import co.init.favorites.domain.FavoritesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FavoriteRepositoryTest {

    private lateinit var movieLocalDataSource: MovieLocalDataSource
    private lateinit var favoriteRepository: FavoritesRepository

    @Before
    fun setup() {
        movieLocalDataSource = mockk(relaxed = true)
        favoriteRepository = FavoritesRepository(movieLocalDataSource)
    }

    @Test
    fun `test get favorite movies`() = runTest {
        val movie = Movie(
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

        val movieList = listOf(movie)
        val pagingData = PagingData.from(movieList)

        coEvery { favoriteRepository.getFavoriteMovies() } returns flowOf(pagingData)

        val expected = flowOf(pagingData).toList()
        val result = favoriteRepository.getFavoriteMovies().toList()

        assertEquals(expected, result)
    }
}