package com.patriciamespert.mygamesac.usecases

import com.patriciamespert.mygamesac.data.datasource.GamesRepository
import javax.inject.Inject

class FindGameUseCase @Inject constructor (private val repository: GamesRepository){

    operator fun invoke(id: Int) = repository.findById(id)

}