package com.patriciamespert.mygamesac.di

import com.patriciamespert.data.datasource.GamesRepository
import com.patriciamespert.data.datasource.detail.GameDetailLocalDataSource
import com.patriciamespert.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.data.datasource.main.GameLocalDataSource
import com.patriciamespert.data.datasource.main.GameRemoteDataSource
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
