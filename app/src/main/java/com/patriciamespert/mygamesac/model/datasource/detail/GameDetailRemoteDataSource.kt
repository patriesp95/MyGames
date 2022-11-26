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

    fun findGameDetails(id: Int, onComplete: (GameDetailResponse) -> Unit){
        val call: Call<GameDetailResponse> = RetrofitHelper.service.getGameDetails(id.toString(), apiKey)
        call.enqueue(object : Callback<GameDetailResponse> {
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