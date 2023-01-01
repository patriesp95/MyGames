<<<<<<<< HEAD:app/src/main/java/com/patriciamespert/mygamesac/data/server/detail/GameDetailServerDataSource.kt
package com.patriciamespert.mygamesac.data.server
========
package com.patriciamespert.mygamesac.framework.server.detail
>>>>>>>> origin/main:app/src/main/java/com/patriciamespert/mygamesac/framework/server/detail/GameDetailServerDataSource.kt

import com.patriciamespert.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.mygamesac.data.datasource.GameDetailResponse
import com.patriciamespert.mygamesac.data.datasource.core.RetrofitHelper
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