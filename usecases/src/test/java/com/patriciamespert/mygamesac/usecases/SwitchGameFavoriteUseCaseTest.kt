package com.patriciamespert.mygamesac.usecases

import com.patriciamespert.mygamesac.data.GamesRepository
import com.patriciamespert.mygamesac.domain.Game
import com.patriciamespert.mygamesac.domain.GameDetail
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class SwitchGameFavoriteUseCaseTest {

    @Test
    fun `Invoke calls games repository`(): Unit = runBlocking {
        val game = sampleGameDetail.copy(gameId = 1)
        val gamesRepository = mock<GamesRepository>()
        val switchGameFavoriteUseCase = SwitchGameFavoriteUseCase(gamesRepository)

        switchGameFavoriteUseCase(game)

        verify(gamesRepository).switchFavorite(game)
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