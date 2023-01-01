package com.patriciamespert.usecases

import com.patriciamespert.data.datasource.GamesRepository
import javax.inject.Inject

class RequestPopularGamesUseCase @Inject constructor(
    private val gamesRepository: GamesRepository
) {

    suspend operator fun invoke() = gamesRepository.requestPopularGames()

}