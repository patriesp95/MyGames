package com.patriciamespert.mygamesac.framework.datasource

import com.patriciamespert.mygamesac.GameResult
import com.patriciamespert.mygamesac.data.core.RetrofitHelper
import com.patriciamespert.mygamesac.data.datasource.main.GameRemoteDataSource
import com.patriciamespert.mygamesac.domain.Game

class GameServerDataSource(
    private val apiKey: String
) : GameRemoteDataSource {
    override suspend fun findPopularGames() =
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
