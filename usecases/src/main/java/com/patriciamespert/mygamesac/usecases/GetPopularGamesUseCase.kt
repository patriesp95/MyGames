package com.patriciamespert.mygamesac.usecases

import com.patriciamespert.mygamesac.data.GamesRepository
import javax.inject.Inject

class GetPopularGamesUseCase @Inject constructor (private val repository: GamesRepository) {

    operator fun invoke() = repository.popularGames

}