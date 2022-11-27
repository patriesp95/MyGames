package com.patriciamespert.mygamesac.domain

import com.patriciamespert.mygamesac.data.GamesRepository

class GetPopularGamesUseCase(private val repository: GamesRepository) {

    operator fun invoke() = repository.popularGames

}