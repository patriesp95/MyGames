package com.patriciamespert.mygamesac.model.datasource.detail

import com.patriciamespert.mygamesac.model.database.detail.GameDetail
import com.patriciamespert.mygamesac.model.database.detail.GameDetailDao
import com.patriciamespert.mygamesac.model.database.main.Game
import kotlinx.coroutines.flow.Flow

class GameDetailLocalDataSource(private val gameDetailDao: GameDetailDao){
    fun findById(id:Int):Flow<GameDetail> = gameDetailDao.findById(id)

    fun checkGameExists(id: Int): Boolean = gameDetailDao.checkGame(id)

    fun save(game: GameDetail){
        gameDetailDao.insertGame(game)
    }

}