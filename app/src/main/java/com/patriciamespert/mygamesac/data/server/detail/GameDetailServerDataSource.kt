package com.patriciamespert.mygamesac.data.server


import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.mygamesac.domain.GameDetail
import com.patriciamespert.mygamesac.data.datasource.core.RetrofitHelper
import com.patriciamespert.mygamesac.di.ApiKey
import retrofit2.Response
import javax.inject.Inject

class GameDetailServerDataSource @Inject constructor(
    @ApiKey private val apiKey: String
) : GameDetailRemoteDataSource {

    override suspend fun findGameDetails(id: Int, onComplete: (GameDetail) -> Unit) {
        val call: Response<GameDetailResponse> = RetrofitHelper.service
            .getGameDetails(
                id.toString(),
                apiKey
            )

        if(call.isSuccessful){
            val game = call.body()
            game?.let {
                it.toDomainModel()
            }
        }
    }
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

