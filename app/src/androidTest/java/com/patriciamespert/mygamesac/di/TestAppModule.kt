package com.patriciamespert.mygamesac.di

import com.patriciamespert.mygamesac.appTestShared.FakeGameDao
import com.patriciamespert.mygamesac.appTestShared.FakeGameDetailDao
import com.patriciamespert.mygamesac.appTestShared.FakeRemoteService
import com.patriciamespert.mygamesac.appTestShared.buildRemoteGames
import com.patriciamespert.mygamesac.data.core.ApiService
import com.patriciamespert.mygamesac.data.database.detail.GameDetailDao
import com.patriciamespert.mygamesac.data.database.main.GameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object TestAppModule {

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(): String = "1234"

    @Provides
    @Singleton
    fun provideGameDao(): GameDao = FakeGameDao()

    @Provides
    @Singleton
    fun provideGameDetailDao(): GameDetailDao = FakeGameDetailDao()

    @Provides
    @Singleton
    fun provideRemoteService(): ApiService =
        FakeRemoteService(buildRemoteGames(1, 2, 3, 4, 5, 6))

}