package com.patriciamespert.data.datasource.main

import com.patriciamespert.domain.Game


interface GameRemoteDataSource {
    suspend fun findPopularGames(): List<Game>?
}
