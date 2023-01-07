package com.patriciamespert.mygamesac.ui.main

import app.cash.turbine.test
import com.patriciamespert.mygamesac.appTestShared.*
import com.patriciamespert.mygamesac.data.server.main.GameResult
import com.patriciamespert.mygamesac.data.database.Game
import com.patriciamespert.mygamesac.data.database.GameDetail
import com.patriciamespert.mygamesac.data.server.detail.GameDetailResponse
import com.patriciamespert.mygamesac.testrules.CoroutinesTestRule
import com.patriciamespert.mygamesac.ui.main.MainViewModel.UiState
import com.patriciamespert.mygamesac.usecases.GetPopularGamesUseCase
import com.patriciamespert.mygamesac.usecases.RequestPopularGamesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainIntegrationTests {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `data is loaded from server when local source is empty`() = runTest {
        val remoteData = buildRemoteGames(4, 5, 6)
        val remoteDetailData = buildRemoteDetailGames(4, 5, 6)
        val vm = buildViewModelWith(
            localData = emptyList(),
            remoteData = remoteData,
            localDetailData = emptyList(),
            remoteDetailData = remoteDetailData
        )

        vm.onUiReady()

        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(games = emptyList()), awaitItem())
            assertEquals(UiState(games = emptyList(), loading = true), awaitItem())
            assertEquals(UiState(games = emptyList(), loading = false), awaitItem())

            val games = awaitItem().games!!
            Assert.assertEquals("Title 4", games[0].name)
            Assert.assertEquals("Title 5", games[1].name)
            Assert.assertEquals("Title 6", games[2].name)

            cancel()
        }
    }

    @Test
    fun `data is loaded from local source when available`() = runTest {
        val localData = buildDatabaseGames(1, 2, 3)
        val remoteData = buildRemoteGames(4, 5, 6)
        val localDetailData = buildDatabaseDetailGames(1,2,3)
        val remoteDetailData = buildRemoteDetailGames(4, 5, 6)
        val vm = buildViewModelWith(
            localData = localData,
            remoteData = remoteData,
            localDetailData = localDetailData,
            remoteDetailData = remoteDetailData
        )

        vm.state.test {
            assertEquals(UiState(), awaitItem())

            val games = awaitItem().games!!
            Assert.assertEquals("Title 1", games[0].name)
            Assert.assertEquals("Title 2", games[1].name)
            Assert.assertEquals("Title 3", games[2].name)

            cancel()
        }
    }

    private fun buildViewModelWith(
        localData: List<Game>,
        remoteData: List<GameResult>,
        localDetailData: List<GameDetail>,
        remoteDetailData: List<GameDetailResponse>
    ): MainViewModel {
        val gamesRepository = buildRepositoryWith(localData, remoteData,localDetailData,remoteDetailData)
        val getPopularMoviesUseCase = GetPopularGamesUseCase(gamesRepository)
        val requestPopularMoviesUseCase = RequestPopularGamesUseCase(gamesRepository)
        return MainViewModel(getPopularMoviesUseCase, requestPopularMoviesUseCase)
    }
}