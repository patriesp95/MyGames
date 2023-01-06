package com.patriciamespert.mygamesac.ui.main

import app.cash.turbine.test
import com.patriciamespert.mygamesac.testrules.CoroutinesTestRule
import com.patriciamespert.mygamesac.testshared.sampleGame
import com.patriciamespert.mygamesac.testshared.sampleGameDetail
import com.patriciamespert.mygamesac.usecases.GetPopularGamesUseCase
import com.patriciamespert.mygamesac.usecases.RequestPopularGamesUseCase
import com.patriciamespert.mygamesac.ui.main.MainViewModel.UiState
import com.patriciamespert.mygamesac.usecases.RequestGameUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getPopularGamesUseCase: GetPopularGamesUseCase

    @Mock
    lateinit var requestPopularGamesUseCase: RequestPopularGamesUseCase

    private lateinit var vm: MainViewModel

    private val games = listOf(sampleGame.copy(id = 1))

    @Before
    fun setup() {
        whenever(getPopularGamesUseCase()).thenReturn(flowOf(games))
        vm = MainViewModel(getPopularGamesUseCase, requestPopularGamesUseCase)
    }

    @Test
    fun `State is updated with current cached content immediately`() = runTest {
        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(loading = false, games=games), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

}