package com.patriciamespert.mygamesac.model.datasource.detail

import com.patriciamespert.mygamesac.model.database.detail.GameDetail
import com.patriciamespert.mygamesac.model.database.detail.GameDetailDao
import kotlinx.coroutines.flow.Flow

class GameDetailLocalDataSource(private val gameDetailDao: GameDetailDao){
    fun findByid(id:Int):Flow<GameDetail> = gameDetailDao.findById(id)
}