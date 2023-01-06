package com.patriciamespert.mygamesac.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.patriciamespert.mygamesac.di.GameId
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {

    @Provides
    @ViewModelScoped
    @GameId
    fun provideGameId(savedStateHandle: SavedStateHandle) =
        DetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id

}