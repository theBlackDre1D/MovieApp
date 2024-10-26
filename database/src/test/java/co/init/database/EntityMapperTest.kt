package co.init.database

import co.init.core.data.Movie
import co.init.database.data.EntityMapper
import co.init.database.data.MovieEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class EntityMapperTest {

    @Test
    fun `test transformation movie to movie entity`() {
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

        val mappedEntityMovie = EntityMapper.toMovieEntity(movie)

        assertEquals(movie.id, mappedEntityMovie.id)
        assertEquals(movie.adult, mappedEntityMovie.adult)
        assertEquals(movie.originalLanguage, mappedEntityMovie.originalLanguage)
        assertEquals(movie.originalTitle, mappedEntityMovie.originalTitle)
        assertEquals(movie.overview, mappedEntityMovie.overview)
        assertEquals(movie.popularity, mappedEntityMovie.popularity, 0.0)
        assertEquals(movie.posterPath, mappedEntityMovie.posterPath)
        assertEquals(movie.releaseDate, mappedEntityMovie.releaseDate)
        assertEquals(movie.title, mappedEntityMovie.title)
        assertEquals(movie.video, mappedEntityMovie.video)
        assertEquals(movie.voteAverage, mappedEntityMovie.voteAverage, 0.0)
        assertEquals(movie.voteCount, mappedEntityMovie.voteCount)
        assertEquals(movie.isFavorite, mappedEntityMovie.isFavorite)
    }

    @Test
    fun `test transformation movie entity to movie`() {
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

        val mappedMovie = EntityMapper.toMovie(movieEntity)

        assertEquals(mappedMovie.id, mappedMovie.id)
        assertEquals(mappedMovie.adult, mappedMovie.adult)
        assertEquals(mappedMovie.originalLanguage, mappedMovie.originalLanguage)
        assertEquals(mappedMovie.originalTitle, mappedMovie.originalTitle)
        assertEquals(mappedMovie.overview, mappedMovie.overview)
        assertEquals(mappedMovie.popularity, mappedMovie.popularity, 0.0)
        assertEquals(mappedMovie.posterPath, mappedMovie.posterPath)
        assertEquals(mappedMovie.releaseDate, mappedMovie.releaseDate)
        assertEquals(mappedMovie.title, mappedMovie.title)
        assertEquals(mappedMovie.video, mappedMovie.video)
        assertEquals(mappedMovie.voteAverage, mappedMovie.voteAverage, 0.0)
        assertEquals(mappedMovie.voteCount, mappedMovie.voteCount)
        assertEquals(mappedMovie.isFavorite, mappedMovie.isFavorite)
    }
}