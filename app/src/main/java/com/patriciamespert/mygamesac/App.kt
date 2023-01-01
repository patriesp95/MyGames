package com.patriciamespert.mygamesac

import android.app.Application
import com.patriciamespert.mygamesac.di.AppComponent
import com.patriciamespert.mygamesac.di.DaggerAppComponent


class App: Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .factory()
            .create(this)
    }
}