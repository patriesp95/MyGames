package com.patriciamespert.mygamesac.di

import android.app.Application
import androidx.room.Room
import com.patriciamespert.data.datasource.detail.GameDetailLocalDataSource
import com.patriciamespert.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.data.datasource.main.GameLocalDataSource
import com.patriciamespert.data.datasource.main.GameRemoteDataSource
import com.patriciamespert.mygamesac.R
import com.patriciamespert.mygamesac.data.database.database.GameDatabase
import com.patriciamespert.mygamesac.data.database.detail.GameDetailRoomDataSource
import com.patriciamespert.mygamesac.data.database.main.GameRoomDataSource
import com.patriciamespert.mygamesac.data.server.GameDetailServerDataSource
import com.patriciamespert.mygamesac.data.server.GameServerDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    @Named("apiKey")
    fun provideApiKey(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        GameDatabase::class.java,
        "game-db"
    ).build()

    @Provides
    fun provideLocalDataSource(db: GameDatabase): GameLocalDataSource =
        GameRoomDataSource(db.gameDao())

    @Provides
    fun provideLocalDetailDataSource(db: GameDatabase): GameDetailLocalDataSource =
        GameDetailRoomDataSource(db.gameDetailDao())

    @Provides
    fun provideRemoteDataSource(@Named("apiKey") apiKey: String): GameRemoteDataSource =
        GameServerDataSource(apiKey)

    @Provides
    fun provideRemoteDetailDataSource(@Named("apiKey") apiKey: String): GameDetailRemoteDataSource =
        GameDetailServerDataSource(apiKey)
}