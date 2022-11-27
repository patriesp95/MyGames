package com.patriciamespert.mygamesac.usecases

import com.patriciamespert.mygamesac.data.GamesRepository

class GetPopularGamesUseCase(private val repository: GamesRepository) {

    operator fun invoke() = repository.popularGames

}