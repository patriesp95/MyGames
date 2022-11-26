package com.patriciamespert.mygamesac.model.database.main

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.patriciamespert.mygamesac.model.database.detail.GameDetail
import com.patriciamespert.mygamesac.model.database.main.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM Game")
    fun getAll(): Flow<List<Game>>

    @Query("SELECT count(id) from Game")
    fun gameCount(): Int

    /*@Query("SELECT * FROM Game WHERE id = :id")
    fun findById(id: Int): Flow<Game>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGames(games: List<Game>)

    @Update
    fun updateGame(game: Game)
}