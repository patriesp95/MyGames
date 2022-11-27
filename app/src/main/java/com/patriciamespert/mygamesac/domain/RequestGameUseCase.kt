package com.patriciamespert.mygamesac.domain

import com.patriciamespert.mygamesac.data.GamesRepository

class RequestGameUseCase(private val repository: GamesRepository) {

    suspend operator fun invoke(id: Int) = repository.requestDetailedGame(id)

}