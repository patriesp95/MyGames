package com.patriciamespert.usecases

import com.patriciamespert.data.datasource.GamesRepository

class RequestGameUseCase(private val repository: GamesRepository) {

    suspend operator fun invoke(id: Int) = repository.requestDetailedGame(id)

}