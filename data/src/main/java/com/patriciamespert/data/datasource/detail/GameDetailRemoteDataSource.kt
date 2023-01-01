package com.patriciamespert.data.datasource.detail

import com.patriciamespert.domain.GameDetail

interface GameDetailRemoteDataSource {
    suspend fun findGameDetails(id: Int, onComplete: (GameDetail) -> Unit)
}