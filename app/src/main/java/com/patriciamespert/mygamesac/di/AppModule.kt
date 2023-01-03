package com.patriciamespert.mygamesac.di

import android.app.Application
import androidx.room.Room
import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameRemoteDataSource
import com.patriciamespert.mygamesac.R
import com.patriciamespert.mygamesac.data.core.ApiService
import com.patriciamespert.mygamesac.data.database.database.GameDatabase
import com.patriciamespert.mygamesac.data.database.detail.GameDetailRoomDataSource
import com.patriciamespert.mygamesac.data.server.database.main.GameRoomDataSource
import com.patriciamespert.mygamesac.data.server.detail.GameDetailServerDataSource
import com.patriciamespert.mygamesac.data.server.main.GameServerDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        GameDatabase::class.java,
        "game-db"
    ).build()


    @Provides
    @Singleton
    fun provideGameDao(db: GameDatabase) = db.gameDao()


    @Provides
    @Singleton
    fun provideGameDetailDao(db: GameDatabase) = db.gameDetailDao()


    @Provides
    @Singleton
    fun provideRemoteService(): ApiService {
        val okHttpClient = HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(this).build()
        }

        return Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }


}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule{
    @Binds
    abstract fun bindLocalDataSource(localDataSource: GameRoomDataSource): GameLocalDataSource

    @Binds
    abstract fun bindLocalDetailDataSource(bindLocalDetailDataSource: GameDetailRoomDataSource): GameDetailLocalDataSource


    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: GameServerDataSource): GameRemoteDataSource


    @Binds
    abstract fun bindRemoteDetailDataSource(remoteDetailRemoteDataSource: GameDetailServerDataSource): GameDetailRemoteDataSource
}