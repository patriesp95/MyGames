package com.patriciamespert.mygamesac.usecases

import com.patriciamespert.mygamesac.data.GamesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class RequestPopularGamesUseCaseTest {

    @Test
    fun `Invoke calls games repository`(): Unit = runBlocking {
        val gamesRepository = mock<GamesRepository>()
        val requestPopularGamesUseCase = RequestPopularGamesUseCase(gamesRepository)

        requestPopularGamesUseCase()

        verify(gamesRepository).requestPopularGames()
    }
}