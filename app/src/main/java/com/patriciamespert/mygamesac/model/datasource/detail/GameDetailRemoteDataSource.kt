package com.patriciamespert.mygamesac.model.datasource.detail

import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.core.RetrofitHelper
import com.patriciamespert.mygamesac.model.database.main.Game
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameDetailRemoteDataSource(
    private val apiKey: String
) {

    fun findGameDetails(id: Int):Call<GameDetailResponse>{
        return RetrofitHelper.service.getGameDetails(id.toString(), apiKey)
    }

    private fun getGameDetails(game: GameDetailResponse):GameDetailResponse {
        return game
    }

    private fun performGameInformationRetrieval(gameCall: Call<GameDetailResponse>, onComplete: (GameDetailResponse) -> Unit){
        gameCall.enqueue(object : Callback<GameDetailResponse> {
            override fun onResponse(
                call: Call<GameDetailResponse>,
                response: Response<GameDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val game = response.body()
                    game?.let {
                        onComplete.invoke(game)
                    }
                }
            }

            override fun onFailure(call: Call<GameDetailResponse>, t: Throwable) {
                print("An error ocurred: ${t.message}")
            }

        })
    }
}