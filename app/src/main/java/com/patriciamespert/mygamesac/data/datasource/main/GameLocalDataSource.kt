package com.patriciamespert.mygamesac.data.datasource.main

import com.patriciamespert.mygamesac.domain.Game
import kotlinx.coroutines.flow.Flow

interface GameLocalDataSource {

    val games: Flow<List<Game>>

    fun isEmpty(): Boolean

    fun save(games: List<Game>)

    suspend fun updateFavorite(id: Int, favorite: Boolean)
}
