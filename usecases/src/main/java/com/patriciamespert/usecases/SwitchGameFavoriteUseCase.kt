package com.patriciamespert.usecases

import com.patriciamespert.data.datasource.GamesRepository
import com.patriciamespert.domain.GameDetail

class SwitchGameFavoriteUseCase(private val repository: GamesRepository) {

    suspend operator fun invoke(game: GameDetail) = repository.switchFavorite(game)

}