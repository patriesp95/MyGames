package com.patriciamespert.mygamesac.data.server.main

import com.patriciamespert.mygamesac.data.datasource.main.GameRemoteDataSource
import com.patriciamespert.mygamesac.data.core.ApiService
import com.patriciamespert.mygamesac.di.ApiKey
import com.patriciamespert.mygamesac.domain.Game
import javax.inject.Inject

class GameServerDataSource @Inject constructor(
    @ApiKey private val apiKey: String,
    private val remoteService: ApiService
) :
    GameRemoteDataSource {
    override suspend fun findPopularGames(): List<Game>? {
        return remoteService
            .listPopularGames(
                apiKey
            ).games?.toDomainModel()
    }

}

private fun List<GameResult>.toDomainModel(): List<Game> = map { it.toDomainModel() }


private fun GameResult.toDomainModel(): Game =
    Game(

        id,
        name,
        released,
        backgroundImage,
        rating,
        ratingTop,
        0,
        false
    )
