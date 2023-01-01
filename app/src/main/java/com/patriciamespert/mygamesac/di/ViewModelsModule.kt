package com.patriciamespert.mygamesac.di

import com.patriciamespert.mygamesac.ui.detail.DetailViewModelFactory
import com.patriciamespert.mygamesac.ui.main.MainViewModelFactory
import com.patriciamespert.usecases.*
import dagger.Module
import dagger.Provides

@Module
object ViewModelsModule {

    @Provides
    fun provideMainViewModelFactory(
        getPopularGamesUseCase: GetPopularGamesUseCase,
        requestPopularGamesUseCase: RequestPopularGamesUseCase
    ) = MainViewModelFactory(getPopularGamesUseCase, requestPopularGamesUseCase)

    @Provides
    fun provideDetailViewModel(
        requestGameUseCase: RequestGameUseCase,
        findGameUseCase: FindGameUseCase,
        switchGameFavoriteUseCase: SwitchGameFavoriteUseCase
    ): DetailViewModelFactory {
        // TODO the id needs to be provided. We'll see it with scoped graphs
        return DetailViewModelFactory(-1,requestGameUseCase, findGameUseCase, switchGameFavoriteUseCase)
    }
}
