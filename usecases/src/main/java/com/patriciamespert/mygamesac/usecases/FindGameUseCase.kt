package com.patriciamespert.mygamesac.usecases

import com.patriciamespert.mygamesac.data.GamesRepository
import com.patriciamespert.mygamesac.domain.GameDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindGameUseCase @Inject constructor (private val repository: GamesRepository){

    operator fun invoke(id: Int): Flow<GameDetail> = repository.findById(id)

}