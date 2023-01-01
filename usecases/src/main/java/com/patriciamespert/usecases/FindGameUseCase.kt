package com.patriciamespert.usecases

import com.patriciamespert.data.datasource.GamesRepository
import javax.inject.Inject

class FindGameUseCase @Inject constructor (private val repository: GamesRepository){

    operator fun invoke(id: Int) = repository.findById(id)

}