package com.patriciamespert.mygamesac.di

import com.patriciamespert.mygamesac.data.datasource.GamesRepository
import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
object DataModule {
    @Provides
    fun provideGamesRepository(
        localDataSource: GameLocalDataSource,
        remoteDataSource: GameRemoteDataSource,
        localGameDetailDataSource: GameDetailLocalDataSource,
        remoteGameDetailDataSource: GameDetailRemoteDataSource,
    ) = GamesRepository(localDataSource, remoteDataSource,localGameDetailDataSource,remoteGameDetailDataSource)
}
