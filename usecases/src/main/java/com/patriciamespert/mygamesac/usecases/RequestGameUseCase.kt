package com.patriciamespert.mygamesac.usecases

import com.patriciamespert.mygamesac.data.GamesRepository
import javax.inject.Inject

class RequestGameUseCase @Inject constructor (private val repository: GamesRepository) {

    suspend operator fun invoke(id: Int) = repository.requestDetailedGame(id)

}