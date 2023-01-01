package com.patriciamespert.mygamesac.data.server


import com.patriciamespert.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.domain.GameDetail
import com.patriciamespert.mygamesac.data.datasource.core.RetrofitHelper
import com.patriciamespert.mygamesac.di.ApiKey
import javax.inject.Inject

class GameDetailServerDataSource @Inject constructor(
    @ApiKey private val apiKey: String
) : GameDetailRemoteDataSource {


    override suspend fun findGameDetails(id: Int, onComplete: (GameDetail) -> Unit) {
        RetrofitHelper.service
            .getGameDetails(
                apiKey
            ).body()?.toDomainModel()
    }

}

private fun List<GameDetailResponse>.toDomainModel(): List<com.patriciamespert.domain.GameDetail> = map {
    it.toDomainModel()
}


private fun GameDetailResponse.toDomainModel(): com.patriciamespert.domain.GameDetail =
    com.patriciamespert.domain.GameDetail(

        gameId,
        gameName,
        gameNameOriginal,
        gameDescription,
        gameBackgroundImage,
        gameRating,
        gameRatingTop,
        false
    )
