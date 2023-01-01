package com.patriciamespert.usecases

import com.patriciamespert.data.datasource.GamesRepository

class GetPopularGamesUseCase(private val repository: GamesRepository) {

    operator fun invoke() = repository.popularGames

}