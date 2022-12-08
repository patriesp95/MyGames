package com.patriciamespert.mygamesac.data.datasource.detail

import com.patriciamespert.mygamesac.GameDetailResponse

interface GameDetailRemoteDataSource {
    suspend fun findGameDetails(id: Int, onComplete: (GameDetailResponse) -> Unit)
}