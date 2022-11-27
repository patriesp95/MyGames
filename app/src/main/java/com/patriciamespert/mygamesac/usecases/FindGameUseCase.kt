package com.patriciamespert.mygamesac.usecases

import com.patriciamespert.mygamesac.data.GamesRepository

class FindGameUseCase(private val repository: GamesRepository){

    operator fun invoke(id: Int) = repository.findById(id)

}