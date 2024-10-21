package co.init.database.domain

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.init.database.data.MOVIE_TABLE_NAME
import co.init.database.data.MovieEntity

@Dao
interface MovieDao {

    // Selects
    @Query("SELECT * FROM $MOVIE_TABLE_NAME WHERE isFavorite = 1")
    fun getAllFavoriteMovies(): PagingSource<Int, MovieEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM $MOVIE_TABLE_NAME WHERE id = :movieId AND isFavorite = 1)")
    suspend fun isMovieFavorite(movieId: Int): Boolean

    @Query("SELECT id FROM $MOVIE_TABLE_NAME WHERE isFavorite = 1")
    suspend fun getAllFavoriteMoviesIds(): List<Int>

    // Inserts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)

    // Delete
    @Delete
    suspend fun deleteMovie(movie: MovieEntity)
}