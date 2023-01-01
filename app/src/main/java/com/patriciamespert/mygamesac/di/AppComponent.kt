package com.patriciamespert.mygamesac.di

import android.app.Application
import com.patriciamespert.mygamesac.ui.detail.DetailFragmentComponent
import com.patriciamespert.mygamesac.ui.detail.DetailFragmentModule
import com.patriciamespert.mygamesac.ui.main.MainFragmentComponent
import com.patriciamespert.mygamesac.ui.main.MainFragmentModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, UseCaseModule::class, ViewModelsModule::class])
interface AppComponent {

    fun plus(module: MainFragmentModule): MainFragmentComponent
    fun plus(module: DetailFragmentModule): DetailFragmentComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }
}