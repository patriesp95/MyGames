package com.patriciamespert.mygamesac.model.database.detail

import androidx.room.*
import com.patriciamespert.mygamesac.model.database.main.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDetailDao {

    @Query("SELECT * FROM GameDetail WHERE gameId = :id")
    fun findById(id: Int): Flow<GameDetail>

    @Query("SELECT count(gameId) from GameDetail WHERE gameId = :id")
    fun checkGame(id:Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: GameDetail)

    @Update(entity = GameDetail::class)
    suspend fun updateGame(games: List<GameDetail>)

}