package com.patriciamespert.mygamesac.data.datasource.main

import com.patriciamespert.mygamesac.domain.Game


interface GameRemoteDataSource {
    suspend fun findPopularGames(): List<Game>?
}
