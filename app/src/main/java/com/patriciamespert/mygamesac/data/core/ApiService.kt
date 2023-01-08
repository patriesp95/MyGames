package com.patriciamespert.mygamesac.data.core

import com.patriciamespert.mygamesac.data.server.detail.GameDetailResponse
import com.patriciamespert.mygamesac.data.server.main.GameResponse

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //@GET("games")
    @GET("games?ordering=-rating&platforms_count=7")
    suspend fun listPopularGames(
        @Query("key") key: String,
//        @Query("ordering") ordering: String = "-rating",
//        @Query("platforms_count") platforms_count: Int = 7,
        //@Query("page") page: Int = 2
    ): GameResponse

    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") id: String,
        @Query("key") key: String,
    ): GameDetailResponse
}
