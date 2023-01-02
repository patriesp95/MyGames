package com.patriciamespert.mygamesac.data.datasource.detail

import com.patriciamespert.mygamesac.domain.GameDetail
import kotlinx.coroutines.flow.Flow

interface GameDetailLocalDataSource {

    fun findById(id: Int): Flow<GameDetail>

    fun checkGameExists(id: Int): Int

    fun save(game: GameDetail)

    suspend fun update(game: GameDetail)
}