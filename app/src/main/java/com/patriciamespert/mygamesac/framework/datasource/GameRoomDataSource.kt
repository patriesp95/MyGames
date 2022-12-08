package com.patriciamespert.mygamesac.framework.datasource

import com.patriciamespert.mygamesac.data.datasource.main.GameLocalDataSource
import com.patriciamespert.mygamesac.framework.database.main.Game as GameDb
import com.patriciamespert.mygamesac.framework.database.main.GameDao
import com.patriciamespert.mygamesac.domain.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRoomDataSource(private val gameDao: GameDao) : GameLocalDataSource {
    override val games: Flow<List<Game>> = gameDao.getAll().map {it.toDomainModel()}

    override fun isEmpty(): Boolean = gameDao.gameCount() == 0

    override fun save(games: List<Game>){
        gameDao.insertGames(games.fromDomainModel())
    }

    override suspend fun updateFavorite(id: Int, favorite: Boolean) {
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