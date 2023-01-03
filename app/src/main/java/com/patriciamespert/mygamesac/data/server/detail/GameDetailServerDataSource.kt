package com.patriciamespert.mygamesac.data.server.detail


import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.mygamesac.domain.GameDetail
import com.patriciamespert.mygamesac.data.core.ApiService
import com.patriciamespert.mygamesac.data.server.detail.GameDetailResponse
import com.patriciamespert.mygamesac.di.ApiKey
import dagger.Provides
import javax.inject.Inject

class GameDetailServerDataSource @Inject constructor(
    @ApiKey private val apiKey: String,
    private val remoteService: ApiService
) : GameDetailRemoteDataSource {
    override suspend fun findGameDetails(id: Int): GameDetail? {
        val game = remoteService.getGameDetails(
            id.toString(),
            apiKey
        )

        print(game)

       return  game?.let { it.toDomainModel() }
    }


    private fun GameDetailResponse.toDomainModel(): GameDetail =
        GameDetail(

            gameId,
            gameName,
            gameNameOriginal,
            gameDescription,
            gameBackgroundImage,
            gameRating,
            gameRatingTop,
            false
        )
}

