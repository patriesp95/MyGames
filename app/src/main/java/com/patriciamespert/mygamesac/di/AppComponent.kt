package com.patriciamespert.mygamesac.di

import android.app.Application
import com.patriciamespert.mygamesac.ui.detail.DetailViewModelFactory
import com.patriciamespert.mygamesac.ui.main.MainViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, UseCaseModule::class, ViewModelsModule::class])
interface AppComponent {

    val mainViewModelFactory: MainViewModelFactory
    val detailViewModelFactory: DetailViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }
}