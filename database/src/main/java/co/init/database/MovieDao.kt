package co.init.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    // Selects
    @Query("SELECT * FROM $MOVIE_TABLE_NAME")
    suspend fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM $MOVIE_TABLE_NAME WHERE id = :userId")
    suspend fun getMovieById(userId: Int): MovieEntity

    // Inserts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<MovieEntity>)
}