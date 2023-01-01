package com.patriciamespert.data.datasource.main

import com.patriciamespert.domain.Game
import kotlinx.coroutines.flow.Flow

interface GameLocalDataSource {

    val games: Flow<List<Game>>

    fun isEmpty(): Boolean

    fun save(games: List<Game>)

    suspend fun updateFavorite(id: Int, favorite: Boolean)
}
