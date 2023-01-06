package com.patriciamespert.mygamesac.data.server.main

import com.patriciamespert.mygamesac.data.datasource.GameResult
import com.patriciamespert.mygamesac.data.datasource.core.RetrofitHelper
import com.patriciamespert.mygamesac.data.datasource.main.GameRemoteDataSource
import com.patriciamespert.mygamesac.di.ApiKey
import com.patriciamespert.mygamesac.domain.Game
import javax.inject.Inject

class GameServerDataSource @Inject constructor(
    @ApiKey private val apiKey: String
) : GameRemoteDataSource {
    override suspend fun findPopularGames() =
        RetrofitHelper.service
            .listPopularGames(
                apiKey
            ).body()?.games?.toDomainModel()
}

private fun List<GameResult>.toDomainModel(): List<Game> = map { it.toDomainModel() }


private fun GameResult.toDomainModel(): Game =
    Game(

        id,
        name,
        released,
        background_image,
        rating,
        rating_top,
        added,
        false
    )
