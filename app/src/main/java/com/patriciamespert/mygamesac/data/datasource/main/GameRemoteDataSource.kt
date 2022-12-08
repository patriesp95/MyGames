package com.patriciamespert.mygamesac.data.datasource.main

import com.patriciamespert.mygamesac.GameResult
import com.patriciamespert.mygamesac.data.core.RetrofitHelper
import com.patriciamespert.mygamesac.domain.Game

class GameRemoteDataSource(
    private val apiKey: String
) {
    suspend fun findPopularGames() =
        RetrofitHelper.service
            .listPopularGames(
                apiKey
            ).body()?.games?.toDomainModel()
}

private fun List<GameResult>.toDomainModel(): List<Game> = map { it.toDomainModel() }


private fun GameResult.toDomainModel(): Game = Game(

        id,
        name,
        released,
        background_image,
        rating,
        rating_top,
        added,
        false
)
