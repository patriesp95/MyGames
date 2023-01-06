package com.patriciamespert.mygamesac.ui.main

import app.cash.turbine.test
import com.patriciamespert.mygamesac.data.GamesRepository
import com.patriciamespert.mygamesac.domain.Game
import com.patriciamespert.mygamesac.domain.GameDetail
import com.patriciamespert.mygamesac.testrules.CoroutinesTestRule
import com.patriciamespert.mygamesac.testshared.sampleGame
import com.patriciamespert.mygamesac.testshared.sampleGameDetail
import com.patriciamespert.mygamesac.ui.FakeLocalDataSource
import com.patriciamespert.mygamesac.ui.FakeLocalDetailDataSource
import com.patriciamespert.mygamesac.ui.FakeRemoteDataSource
import com.patriciamespert.mygamesac.ui.FakeRemoteDetailDataSource
import com.patriciamespert.mygamesac.ui.main.MainViewModel.UiState
import com.patriciamespert.mygamesac.usecases.GetPopularGamesUseCase
import com.patriciamespert.mygamesac.usecases.RequestPopularGamesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainIntegrationTests {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `data is loaded from server when local source is empty`() = runTest {
        val remoteData = listOf(sampleGame.copy(1), sampleGame.copy(2))
        val vm = buildViewModelWith(
            localData = emptyList(),
            remoteData = remoteData
        )

        vm.onUiReady()

        vm.state.test {
            Assert.assertEquals(UiState(), awaitItem())
            Assert.assertEquals(UiState(games = emptyList()), awaitItem())
            Assert.assertEquals(UiState(games = emptyList(), loading = true), awaitItem())
            Assert.assertEquals(UiState(games = emptyList(), loading = false), awaitItem())
            Assert.assertEquals(UiState(games = remoteData, loading = false), awaitItem())
            cancel()
        }
    }

    @Test
    fun `data is loaded from local source when available`() = runTest {
        val localData = listOf(sampleGame.copy(10), sampleGame.copy(11))
        val remoteData = listOf(sampleGame.copy(1), sampleGame.copy(2))
        val vm = buildViewModelWith(
            localData = localData,
            remoteData = remoteData
        )

        vm.state.test {
            Assert.assertEquals(UiState(), awaitItem())
            Assert.assertEquals(UiState(loading=false,games = localData), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}

private fun buildViewModelWith(
    localData: List<Game> = emptyList(),
    localDetailData: List<GameDetail> = emptyList(),
    remoteData: List<Game> = emptyList(),
    remoteDetailData: List<GameDetail> = emptyList(),
    ): MainViewModel {
    val localDataSource = FakeLocalDataSource().apply { inMemoryGames.value = localData }
    val remoteDataSource = FakeRemoteDataSource().apply { games = remoteData }
    val localDetailDataSource = FakeLocalDetailDataSource().apply { inMemoryDetailedGames.value = localDetailData }
    val remoteDetailDataSource = FakeRemoteDetailDataSource().apply { detailedGames = remoteDetailData }
    val gamesRepository = GamesRepository(localDataSource, remoteDataSource, localDetailDataSource,remoteDetailDataSource)
    val getPopularMoviesUseCase = GetPopularGamesUseCase(gamesRepository)
    val requestPopularMoviesUseCase = RequestPopularGamesUseCase(gamesRepository)
    return MainViewModel(getPopularMoviesUseCase, requestPopularMoviesUseCase)
}