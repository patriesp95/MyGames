package com.patriciamespert.mygamesac.model.datasource.main

import com.patriciamespert.mygamesac.core.RetrofitHelper

class GameRemoteDataSource(
    private val apiKey: String
) {
    suspend fun findPopularGames() =
        RetrofitHelper.service
            .listPopularGames(
                apiKey
            )
}
