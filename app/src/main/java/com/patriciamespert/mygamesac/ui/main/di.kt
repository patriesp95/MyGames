package com.patriciamespert.mygamesac.ui.main

import com.patriciamespert.usecases.GetPopularGamesUseCase
import com.patriciamespert.usecases.RequestPopularGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainFragmentModule {

    @Provides
    fun mainViewModelFactoryProvider(
        getPopularGamesUseCase: GetPopularGamesUseCase,
        requestPopularGamesUseCase: RequestPopularGamesUseCase
    ) = MainViewModelFactory(getPopularGamesUseCase, requestPopularGamesUseCase)
}

@Subcomponent(modules = [(MainFragmentModule::class)])
interface MainFragmentComponent {
    val mainViewModelFactory: MainViewModelFactory
}