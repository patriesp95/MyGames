package com.patriciamespert.mygamesac.model

import android.app.Application
import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.GameResult
import com.patriciamespert.mygamesac.R
import com.patriciamespert.mygamesac.core.RetrofitHelper
import retrofit2.Call

class GamesRepository(application: Application) {

    private val apiKey = application.getString(R.string.api_key)

    suspend fun findPopularGames() =
        RetrofitHelper.service
            .listPopularGames(
                apiKey
            )

    fun findGameDetails(game: GameResult): Call<GameDetailResponse> {
        val id = game.id.toString()
        return RetrofitHelper.service.getGameDetails(id, apiKey)
    }
}