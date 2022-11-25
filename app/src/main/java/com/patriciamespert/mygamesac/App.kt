package com.patriciamespert.mygamesac

import android.app.Application
import androidx.room.Room
import com.patriciamespert.mygamesac.model.database.GameDatabase

class App: Application() {

    lateinit var db: GameDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            GameDatabase::class.java,"game-db"
        ).build()
    }
}