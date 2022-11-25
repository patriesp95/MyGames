package com.patriciamespert.mygamesac.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GameDatabase: RoomDatabase(){

    abstract fun gameDao(): GameDao
}