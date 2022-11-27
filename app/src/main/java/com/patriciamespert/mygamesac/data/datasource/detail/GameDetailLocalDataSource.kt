package com.patriciamespert.mygamesac.data.datasource.detail

import com.patriciamespert.mygamesac.domain.GameDetail
import com.patriciamespert.mygamesac.data.database.detail.GameDetailDao
import kotlinx.coroutines.flow.Flow

class GameDetailLocalDataSource(private val gameDetailDao: GameDetailDao){
    fun findById(id:Int):Flow<GameDetail> = gameDetailDao.findById(id)

    fun checkGameExists(id: Int): Boolean = gameDetailDao.checkGame(id)

    fun save(game: GameDetail){
        gameDetailDao.insertGame(game)
    }

    suspend fun update(game: GameDetail) {
        gameDetailDao.updateGame(listOf(game))
    }

}