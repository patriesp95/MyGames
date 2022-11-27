package com.patriciamespert.mygamesac.model.datasource.main

import com.patriciamespert.mygamesac.model.database.main.Game
import com.patriciamespert.mygamesac.model.database.main.GameDao
import kotlinx.coroutines.flow.Flow

class GameLocalDataSource(private val gameDao: GameDao){
    val games: Flow<List<Game>> = gameDao.getAll()

    fun isEmpty(): Boolean = gameDao.gameCount() == 0

    fun save(games: List<Game>){
        gameDao.insertGames(games)
    }

    suspend fun updateFavorite(id: Int, favorite: Boolean) {
        gameDao.updateFavorite(id, favorite)
    }
}