package com.patriciamespert.usecases

import com.patriciamespert.data.datasource.GamesRepository

class FindGameUseCase(private val repository: GamesRepository){

    operator fun invoke(id: Int) = repository.findById(id)

}