package com.patriciamespert.mygamesac.data.datasource.main

import com.patriciamespert.mygamesac.data.database.main.Game as GameDb
import com.patriciamespert.mygamesac.data.database.main.GameDao
import com.patriciamespert.mygamesac.domain.Game
import com.patriciamespert.mygamesac.domain.GameDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameLocalDataSource(private val gameDao: GameDao){
    val games: Flow<List<Game>> = gameDao.getAll().map {it.toDomainModel()}

    fun isEmpty(): Boolean = gameDao.gameCount() == 0

    fun save(games: List<Game>){
        gameDao.insertGames(games.fromDomainModel())
    }

    suspend fun updateFavorite(id: Int, favorite: Boolean) {
        gameDao.updateFavorite(id, favorite)
    }
}

private fun List<Game>.fromDomainModel(): List<GameDb> = map { it.fromDomainModel() }

private fun Game.fromDomainModel(): GameDb = GameDb(
        id,
        name,
        released,
        backgroundImage,
        rating,
        ratingTop,
        added,
        favorite
)

private fun List<GameDb>.toDomainModel(): List<Game> = map { it.toDomainModel() }

private fun GameDb.toDomainModel(): Game = Game(
        id,
        name,
        released,
        backgroundImage,
        rating,
        ratingTop,
        added,
        favorite
)