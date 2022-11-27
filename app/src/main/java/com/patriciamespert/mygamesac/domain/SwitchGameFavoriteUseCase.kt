package com.patriciamespert.mygamesac.domain

import com.patriciamespert.mygamesac.data.GamesRepository
import com.patriciamespert.mygamesac.data.database.detail.GameDetail

class SwitchGameFavoriteUseCase(private val repository: GamesRepository) {

    suspend operator fun invoke(game: GameDetail) = repository.switchFavorite(game)

}