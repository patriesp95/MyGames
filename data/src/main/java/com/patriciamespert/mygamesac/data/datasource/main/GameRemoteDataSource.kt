package com.patriciamespert.mygamesac.data.datasource.main

import arrow.core.Either
import com.patriciamespert.mygamesac.domain.Game


interface GameRemoteDataSource {
    suspend fun findPopularGames(): List<Game>?
}
