package co.init.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    // Selects
    @Query("SELECT * FROM $MOVIE_TABLE_NAME")
    fun getAllMoviesPaged(): PagingSource<Int, MovieEntity >

    // Inserts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)

    // Delete
    @Delete
    suspend fun deleteMovie(movie: MovieEntity)
}