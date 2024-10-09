package co.init.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    // Selects
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