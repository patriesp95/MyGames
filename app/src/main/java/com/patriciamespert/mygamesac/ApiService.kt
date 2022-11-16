package com.patriciamespert.mygamesac

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("games?ordering=-rating")
    suspend fun listPopularVideogames(
        @Query("key") key: String
    ): Response<GameResponse>
}
