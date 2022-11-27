package com.patriciamespert.mygamesac.usecases

import com.patriciamespert.mygamesac.data.GamesRepository
import com.patriciamespert.mygamesac.domain.GameDetail

class SwitchGameFavoriteUseCase(private val repository: GamesRepository) {

    suspend operator fun invoke(game: GameDetail) = repository.switchFavorite(game)

}