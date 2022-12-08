package com.patriciamespert.mygamesac.framework.datasource

import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.data.core.RetrofitHelper
import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailRemoteDataSource
import retrofit2.Response

class GameDetailServerDataSource(
    private val apiKey: String
) : GameDetailRemoteDataSource {

    override suspend fun findGameDetails(id: Int, onComplete: (GameDetailResponse) -> Unit){
        val call: Response<GameDetailResponse> = RetrofitHelper.service.getGameDetails(id.toString(), apiKey)
        if(call.isSuccessful){
            val game = call.body()
            game?.let {
                onComplete.invoke(game)
            }
        }

    }
}