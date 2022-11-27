package com.patriciamespert.mygamesac.ui.main

import androidx.lifecycle.*
import com.patriciamespert.mygamesac.data.GamesRepository
import com.patriciamespert.mygamesac.data.database.main.Game
import com.patriciamespert.mygamesac.domain.GetPopularGamesUseCase
import com.patriciamespert.mygamesac.domain.RequestPopularGamesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPopularGamesUseCase: GetPopularGamesUseCase,
    private val requestPopularGamesUseCase: RequestPopularGamesUseCase
): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getPopularGamesUseCase()
                .collect{games -> _state.update { UiState(games =  games) }}

        }

        onUiReady()
    }

    private fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            requestPopularGamesUseCase()
            _state.value = UiState(loading = false)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val games: List<Game>? = null
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val getPopularGamesUseCase: GetPopularGamesUseCase,
    private val requestPopularGamesUseCase: RequestPopularGamesUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getPopularGamesUseCase,requestPopularGamesUseCase) as T
    }
}