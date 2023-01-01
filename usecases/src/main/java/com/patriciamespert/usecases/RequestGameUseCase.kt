package com.patriciamespert.usecases

import com.patriciamespert.data.datasource.GamesRepository
import javax.inject.Inject

class RequestGameUseCase @Inject constructor (private val repository: GamesRepository) {

    suspend operator fun invoke(id: Int) = repository.requestDetailedGame(id)

}