package com.patriciamespert.mygamesac.ui.detail

import com.patriciamespert.mygamesac.usecases.FindGameUseCase
import com.patriciamespert.mygamesac.usecases.RequestGameUseCase
import com.patriciamespert.mygamesac.usecases.SwitchGameFavoriteUseCase
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