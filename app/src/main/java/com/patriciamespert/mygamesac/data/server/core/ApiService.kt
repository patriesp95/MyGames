package com.patriciamespert.mygamesac.data.datasource.core

import com.patriciamespert.mygamesac.data.datasource.GameDetailResponse
import com.patriciamespert.mygamesac.data.server.main.GameResponse

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
