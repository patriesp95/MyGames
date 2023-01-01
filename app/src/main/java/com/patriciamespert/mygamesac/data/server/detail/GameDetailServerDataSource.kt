package com.patriciamespert.mygamesac.data.server


import com.patriciamespert.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.mygamesac.data.datasource.GameDetailResponse
import com.patriciamespert.mygamesac.data.datasource.core.RetrofitHelper
import com.patriciamespert.mygamesac.domain.GameDetail
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