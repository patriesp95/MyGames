package com.patriciamespert.mygamesac.data.database.detail

import androidx.room.*
import com.patriciamespert.mygamesac.data.database.GameDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDetailDao {

    @Query("SELECT * FROM GameDetail WHERE gameId = :id")
    fun findById(id: Int): Flow<GameDetail>

    @Query("SELECT count(gameId) from GameDetail WHERE gameId = :id")
    fun checkGame(id:Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: GameDetail)

    @Update(entity = GameDetail::class)
    suspend fun updateGame(games: List<GameDetail>): Int

}