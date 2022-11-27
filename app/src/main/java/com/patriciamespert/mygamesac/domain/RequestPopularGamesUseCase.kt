package com.patriciamespert.mygamesac.domain

import com.patriciamespert.mygamesac.data.GamesRepository

class RequestPopularGamesUseCase(
    private val gamesRepository: GamesRepository
) {

    suspend operator fun invoke() = gamesRepository.requestPopularGames()

}