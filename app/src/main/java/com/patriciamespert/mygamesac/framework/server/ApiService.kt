package com.patriciamespert.mygamesac.framework.server

import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.framework.server.main.GameResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun listPopularGames(
        @Query("key") key: String,
        @Query("ordering") ordering: String = "-rating",
        @Query("platforms_count") platforms_count: Int = 7,
        //@Query("page") page: Int = 2
    ): Response<GameResponse>

    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") id: String,
        @Query("key") key: String
        ): Response<GameDetailResponse>
}
