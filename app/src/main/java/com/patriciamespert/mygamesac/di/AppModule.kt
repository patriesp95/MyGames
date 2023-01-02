package com.patriciamespert.mygamesac.di

import android.app.Application
import androidx.room.Room
import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameRemoteDataSource
import com.patriciamespert.mygamesac.R
import com.patriciamespert.mygamesac.data.database.database.GameDatabase
import com.patriciamespert.mygamesac.data.database.detail.GameDetailRoomDataSource
import com.patriciamespert.mygamesac.data.database.main.GameRoomDataSource
import com.patriciamespert.mygamesac.data.server.GameDetailServerDataSource
import com.patriciamespert.mygamesac.data.server.GameServerDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        GameDatabase::class.java,
        "game-db"
    ).build()


    @Provides
    @Singleton
    fun provideGameDao(db: GameDatabase) = db.gameDao()


    @Provides
    @Singleton
    fun provideGameDetailDao(db: GameDatabase) = db.gameDetailDao()


}

@Module
abstract class AppDataModule{
    @Binds
    abstract fun bindLocalDataSource(localDataSource: GameRoomDataSource): GameLocalDataSource

    @Binds
    abstract fun bindLocalDetailDataSource(bindLocalDetailDataSource: GameDetailRoomDataSource): GameDetailLocalDataSource


    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: GameServerDataSource): GameRemoteDataSource


    @Binds
    abstract fun bindRemoteDetailDataSource(remoteDetailRemoteDataSource: GameDetailServerDataSource): GameDetailRemoteDataSource
}