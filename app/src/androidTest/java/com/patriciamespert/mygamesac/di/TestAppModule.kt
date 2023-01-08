package com.patriciamespert.mygamesac.di

import com.patriciamespert.mygamesac.appTestShared.FakeGameDao
import com.patriciamespert.mygamesac.appTestShared.FakeGameDetailDao
import com.patriciamespert.mygamesac.appTestShared.FakeRemoteService
import com.patriciamespert.mygamesac.appTestShared.buildRemoteGames
import com.patriciamespert.mygamesac.data.core.ApiService
import com.patriciamespert.mygamesac.data.database.detail.GameDetailDao
import com.patriciamespert.mygamesac.data.database.main.GameDao
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
    fun provideApiKey(): String = "60c65bc880424d0aa952d4684512bb1b"

    @Provides
    @Singleton
    fun provideGameDao(): GameDao = FakeGameDao()

    @Provides
    @Singleton
    fun provideGameDetailDao(): GameDetailDao = FakeGameDetailDao()


    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "https://api.rawg.io/api/"

   /* @Provides
    @Singleton
    fun provideRemoteService(): ApiService =
        FakeRemoteService(buildRemoteGames(1, 2, 3, 4, 5, 6))*/


    @Provides
    @Singleton
    fun provideRemoteService(@ApiUrl apiUrl: String): ApiService {
        val okHttpClient = HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(this).build()
        }

        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

}