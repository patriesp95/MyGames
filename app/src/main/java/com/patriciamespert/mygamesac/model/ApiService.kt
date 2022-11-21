package com.patriciamespert.mygamesac.model

import com.patriciamespert.mygamesac.GameDetailResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun listPopularVideogames(
        @Query("key") key: String,
        @Query("ordering") ordering: String = "-rating",
        @Query("platforms_count") platforms_count: Int = 7,
        //@Query("page") page: Int = 2
    ): Response<GameResponse>

    @GET("games/{id}")
    fun getGameDetails(
        @Path("id") id: String,
        @Query("key") key: String
        ): Call<GameDetailResponse>
}
