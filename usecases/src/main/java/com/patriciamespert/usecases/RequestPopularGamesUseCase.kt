package com.patriciamespert.usecases

import com.patriciamespert.data.datasource.GamesRepository

class RequestPopularGamesUseCase(
    private val gamesRepository: GamesRepository
) {

    suspend operator fun invoke() = gamesRepository.requestPopularGames()

}