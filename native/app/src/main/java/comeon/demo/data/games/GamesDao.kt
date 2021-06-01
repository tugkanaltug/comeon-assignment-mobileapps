package comeon.demo.data.games

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GamesDao {

    @Query("SELECT * FROM games")
    fun get(): LiveData<List<Games>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(games: List<Games>)
}