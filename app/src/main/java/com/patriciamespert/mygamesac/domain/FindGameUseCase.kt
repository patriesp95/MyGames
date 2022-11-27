package com.patriciamespert.mygamesac.domain

import com.patriciamespert.mygamesac.data.GamesRepository

class FindGameUseCase(private val repository: GamesRepository){

    operator fun invoke(id: Int) = repository.findById(id)

}