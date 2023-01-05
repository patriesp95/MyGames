package com.patriciamespert.mygamesac.usecases

import com.patriciamespert.mygamesac.domain.GameDetail
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class RequestPopularGamesUseCaseTest {

    @Test
    fun `Invoke calls games repository`(): Unit = runBlocking {
        val game = flowOf(sampleGameDetail.copy(gameId = 1))
        val requestGameUseCase = RequestGameUseCase(mock() {
            on { findById(1) } doReturn (game)
        })

        val result = requestGameUseCase(1)

        assertEquals(game, result)
    }
}

private val sampleGameDetail = GameDetail(
    0,
    "Title",
    "Name",
    "01/01/2025",
    "",
    5.0,
    5,
    false
)