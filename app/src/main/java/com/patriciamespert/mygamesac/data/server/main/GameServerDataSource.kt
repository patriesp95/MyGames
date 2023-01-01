package com.patriciamespert.mygamesac.data.server

import com.patriciamespert.mygamesac.data.datasource.GameResult
import com.patriciamespert.mygamesac.data.datasource.core.RetrofitHelper
import com.patriciamespert.data.datasource.main.GameRemoteDataSource
import com.patriciamespert.mygamesac.di.ApiKey
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

private fun List<GameResult>.toDomainModel(): List<com.patriciamespert.domain.Game> = map { it.toDomainModel() }


private fun GameResult.toDomainModel(): com.patriciamespert.domain.Game =
    com.patriciamespert.domain.Game(

        id,
        name,
        released,
        background_image,
        rating,
        rating_top,
        added,
        false
    )
