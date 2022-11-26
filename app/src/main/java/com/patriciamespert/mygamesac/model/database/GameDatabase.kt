package com.patriciamespert.mygamesac.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.patriciamespert.mygamesac.model.database.detail.GameDetail
import com.patriciamespert.mygamesac.model.database.detail.GameDetailDao
import com.patriciamespert.mygamesac.model.database.main.Game
import com.patriciamespert.mygamesac.model.database.main.GameDao

@Database(entities = [Game::class, GameDetail::class], version = 1, exportSchema = false)
abstract class GameDatabase: RoomDatabase(){

    abstract fun gameDao(): GameDao
    abstract fun gameDetailDao(): GameDetailDao
}