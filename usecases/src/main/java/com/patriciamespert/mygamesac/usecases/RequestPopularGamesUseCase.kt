package com.patriciamespert.mygamesac.usecases

import com.patriciamespert.mygamesac.data.datasource.GamesRepository
import javax.inject.Inject

class RequestPopularGamesUseCase @Inject constructor(
    private val gamesRepository: GamesRepository
) {

    suspend operator fun invoke() = gamesRepository.requestPopularGames()

}