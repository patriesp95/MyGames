package com.patriciamespert.mygamesac.data.datasource.detail

import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.core.RetrofitHelper
import retrofit2.Response

class GameDetailRemoteDataSource(
    private val apiKey: String
) {

    suspend fun findGameDetails(id: Int, onComplete: (GameDetailResponse) -> Unit){
        val call: Response<GameDetailResponse> = RetrofitHelper.service.getGameDetails(id.toString(), apiKey)
        if(call.isSuccessful){
            val game = call.body()
            game?.let {
                onComplete.invoke(game)
            }
        }

    }
}