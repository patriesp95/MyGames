package com.patriciamespert.mygamesac.di

import com.patriciamespert.data.datasource.GamesRepository
import com.patriciamespert.usecases.*
import dagger.Module
import dagger.Provides

@Module
object UseCaseModule {

    @Provides
    fun provideGetPopularGamesUseCase(gamesRepository: GamesRepository) =
        GetPopularGamesUseCase(gamesRepository)

    @Provides
    fun provideRequestPopularGamesUseCase(gamesRepository: GamesRepository) =
        RequestPopularGamesUseCase(gamesRepository)

    @Provides
    fun provideRequestGameUseCase(gamesRepository: GamesRepository) =
        RequestGameUseCase(gamesRepository)

    @Provides
    fun provideFindGameUseCase(gamesRepository: GamesRepository) = FindGameUseCase(gamesRepository)

    @Provides
    fun provideSwitchGameFavoriteUseCase(gamesRepository: GamesRepository) =
        SwitchGameFavoriteUseCase(gamesRepository)
}