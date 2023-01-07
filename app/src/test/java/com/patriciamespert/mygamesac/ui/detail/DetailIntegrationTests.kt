package com.patriciamespert.mygamesac.ui.detail

import app.cash.turbine.test
import com.patriciamespert.mygamesac.appTestShared.buildDatabaseDetailGames
import com.patriciamespert.mygamesac.data.database.Game
import com.patriciamespert.mygamesac.data.database.GameDetail as DatabaseDetailGame
import com.patriciamespert.mygamesac.data.server.main.GameResult as RemoteGame
import com.patriciamespert.mygamesac.data.server.detail.GameDetailResponse as RemoteDetailGame
import com.patriciamespert.mygamesac.domain.GameDetail
import com.patriciamespert.mygamesac.testrules.CoroutinesTestRule
import com.patriciamespert.mygamesac.testshared.sampleGameDetail
import com.patriciamespert.mygamesac.appTestShared.buildRepositoryWith
import com.patriciamespert.mygamesac.ui.detail.DetailViewModel.UiState
import com.patriciamespert.mygamesac.usecases.FindGameUseCase
import com.patriciamespert.mygamesac.usecases.RequestGameUseCase
import com.patriciamespert.mygamesac.usecases.SwitchGameFavoriteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailIntegrationTests {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `UI is updated with the game on start`() = runTest {
        val vm = buildViewModelWith(
            id = 2,
            localDetailData = buildDatabaseDetailGames(1, 2, 3)
        )

        vm.state.test {
            Assert.assertEquals(UiState(), awaitItem())
            Assert.assertEquals(2, awaitItem().game!!.gameId)
            cancel()
        }
    }

    @Test
    fun `Favorite is updated in local data source`() = runTest {
        val vm = buildViewModelWith(
            id = 2,
            localDetailData = buildDatabaseDetailGames(1, 2, 3)
        )

        vm.onFavoriteClicked()

        vm.state.test {
            Assert.assertEquals(UiState(), awaitItem())
            Assert.assertEquals(false, awaitItem().game!!.favorite)
            Assert.assertEquals(true, awaitItem().game!!.favorite)
            cancel()
        }
    }

    private fun buildViewModelWith(
        id: Int,
        localData: List<Game> = emptyList(),
        localDetailData: List<DatabaseDetailGame> = emptyList(),
        remoteData: List<RemoteGame> = emptyList(),
        remoteDetailData: List<RemoteDetailGame> = emptyList(),
    ): DetailViewModel {
        val gamesRepository = buildRepositoryWith(localData, remoteData, localDetailData, remoteDetailData)
        val requestGameUseCase = RequestGameUseCase(gamesRepository)
        val findGameUseCase = FindGameUseCase(gamesRepository)
        val switchGameFavoriteUseCase = SwitchGameFavoriteUseCase(gamesRepository)
        return DetailViewModel(id, requestGameUseCase,findGameUseCase, switchGameFavoriteUseCase)
    }
}