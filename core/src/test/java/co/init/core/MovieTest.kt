package co.init.core

import co.init.core.data.Movie
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.Serializable

class MovieTest {

    @Test
    fun `test Movie object creation with all parameters`() {
        // Given
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

        // Then
        assertEquals(1, movie.id)
        assertFalse(movie.adult)
        assertEquals("en", movie.originalLanguage)
        assertEquals("Original Title", movie.originalTitle)
        assertEquals("This is an overview of the movie.", movie.overview)
        assertEquals(100.0, movie.popularity, 0.0)
        assertEquals("/poster.jpg", movie.posterPath)
        assertEquals("2023-10-01", movie.releaseDate)
        assertEquals("Movie Title", movie.title)
        assertFalse(movie.video)
        assertEquals(8.5, movie.voteAverage, 0.0)
        assertEquals(2000, movie.voteCount)
        assertTrue(movie.isFavorite)
    }

    @Test
    fun `test default value for isFavorite`() {
        // Given
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
            voteCount = 2000
        )

        // Then
        assertFalse(movie.isFavorite) // Default value should be false
    }

    @Test
    fun `test thumbnailUrl and imageUrl are generated correctly`() {
        // Given
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
            voteCount = 2000
        )

        // When
        val expectedThumbnailUrl = "https://image.tmdb.org/t/p/w92/poster.jpg"
        val expectedImageUrl = "https://image.tmdb.org/t/p/original/poster.jpg"

        // Then
        assertEquals(expectedThumbnailUrl, movie.thumbnailUrl)
        assertEquals(expectedImageUrl, movie.imageUrl)
    }

    @Test
    fun `test if Movie has annotation Serializable`() {
        // Given
        val originalMovie = Movie(
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

        val serialized = Json.encodeToString(originalMovie)
        val deserialized = Json.decodeFromString<Movie>(serialized)

        assertEquals(originalMovie, deserialized)
    }

    @Test
    fun `check if Movie implements Serializable interface`() {
        assertTrue(Movie::class.java.interfaces.contains(Serializable::class.java))
    }
}
