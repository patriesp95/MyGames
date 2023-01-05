package com.patriciamespert.mygamesac.usecases

import com.patriciamespert.mygamesac.domain.Game
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetPopularGamesUseCaseTest {

    @Test
    fun `Invoke calls games repository`(): Unit = runBlocking {
        val games = flowOf(listOf(sampleGame.copy(id = 1)))
        val getPopularGamesUseCase = GetPopularGamesUseCase(mock {
            on { popularGames } doReturn games
        })

        val result = getPopularGamesUseCase()

        assertEquals(games, result)
    }
}

private val sampleGame = Game(
    0,
    "Title",
    "Name",
    "01/01/2025",
    5.0,
    5,
    0,
    false
)