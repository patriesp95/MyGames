package com.patriciamespert.mygamesac

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun listPopularVideogames(
        @Query("key") key: String,
        @Query("ordering") ordering: String = "-rating",
        @Query("page") page: Int = 2
    ): Response<GameResponse>

    @GET("games/{id}")
    fun getGameDetails(
        @Path("id") id: Int,
        @Query("key") key: String
        ): Response<GameDetailResponse>
}
