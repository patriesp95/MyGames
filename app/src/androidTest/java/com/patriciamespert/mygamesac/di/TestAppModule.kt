package com.patriciamespert.mygamesac.di

import android.app.Application
import com.patriciamespert.mygamesac.data.core.ApiService
import com.patriciamespert.mygamesac.R
import com.patriciamespert.mygamesac.data.database.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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
    fun provideApiKey(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun provideGameDao(db: GameDatabase) = db.gameDao()

    @Provides
    @Singleton
    fun provideGameDetailDao(db: GameDatabase) = db.gameDetailDao()


    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "http://localhost:8080"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }


    @Provides
    @Singleton
    fun provideRemoteService(@ApiUrl apiUrl: String, okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

}