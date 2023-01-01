package com.patriciamespert.usecases

import com.patriciamespert.data.datasource.GamesRepository
import javax.inject.Inject

class GetPopularGamesUseCase @Inject constructor (private val repository: GamesRepository) {

    operator fun invoke() = repository.popularGames

}