package com.patriciamespert.mygamesac.ui.detail

import com.patriciamespert.usecases.FindGameUseCase
import com.patriciamespert.usecases.RequestGameUseCase
import com.patriciamespert.usecases.SwitchGameFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class DetailFragmentModule(private val gameId: Int) {

    @Provides
    fun detailViewModelFactoryProvider(
        requestGameUseCase: RequestGameUseCase,
        findGameUseCase: FindGameUseCase,
        switchGameFavoriteUseCase: SwitchGameFavoriteUseCase
    ) = DetailViewModelFactory(gameId, requestGameUseCase,findGameUseCase,switchGameFavoriteUseCase)

}

@Subcomponent(modules = [(DetailFragmentModule::class)])
interface DetailFragmentComponent {
    val detailViewModelFactory: DetailViewModelFactory
}