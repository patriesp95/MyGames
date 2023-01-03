package com.patriciamespert.mygamesac.data.datasource.detail

import com.patriciamespert.mygamesac.domain.GameDetail

interface GameDetailRemoteDataSource {
    suspend fun findGameDetails(id: Int): GameDetail?
}