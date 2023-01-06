package com.patriciamespert.mygamesac.ui.detail

import app.cash.turbine.test
import com.patriciamespert.mygamesac.testrules.CoroutinesTestRule
import com.patriciamespert.mygamesac.testshared.sampleGameDetail
import com.patriciamespert.mygamesac.ui.main.MainViewModel
import com.patriciamespert.mygamesac.usecases.FindGameUseCase
import com.patriciamespert.mygamesac.usecases.RequestGameUseCase
import com.patriciamespert.mygamesac.usecases.SwitchGameFavoriteUseCase
import com.patriciamespert.mygamesac.ui.detail.DetailViewModel.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var requestGameUseCase: RequestGameUseCase

    @Mock
    lateinit var findGameUseCase: FindGameUseCase

    @Mock
    lateinit var switchGameFavoriteUseCase: SwitchGameFavoriteUseCase

    private lateinit var vm: DetailViewModel

    private val game = sampleGameDetail.copy(gameId = 2)

    @Before
    fun setup() {
        whenever(findGameUseCase(2)).thenReturn(flowOf(game))
        vm = DetailViewModel(2, requestGameUseCase,findGameUseCase, switchGameFavoriteUseCase)
    }

    @Test
    fun `UI is updated with the movie on start`() = runTest {
        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(game = game), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Favorite action calls the corresponding use case`() = runTest {
        vm.onFavoriteClicked()
        runCurrent()

        verify(switchGameFavoriteUseCase).invoke(game)
    }
}