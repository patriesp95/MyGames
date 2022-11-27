package com.patriciamespert.mygamesac.data.database.main

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.patriciamespert.mygamesac.domain.Game
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

    @Query("UPDATE game SET favorite =:favorite WHERE id = :id")
    suspend fun updateFavorite(id: Int, favorite: Boolean)
}