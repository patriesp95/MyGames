package com.patriciamespert.data.datasource.detail

import kotlinx.coroutines.flow.Flow

interface GameDetailLocalDataSource {

    fun findById(id: Int): Flow<com.patriciamespert.domain.GameDetail>

    fun checkGameExists(id: Int): Int

    fun save(game: com.patriciamespert.domain.GameDetail)

    suspend fun update(game: com.patriciamespert.domain.GameDetail)
}