package com.patriciamespert.mygamesac.ui.main

import androidx.lifecycle.*
import com.patriciamespert.mygamesac.data.GamesRepository
import com.patriciamespert.mygamesac.data.database.main.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val gamesRepository: GamesRepository): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            gamesRepository.popularGames.collect{ games ->
                _state.value = UiState(games =  games)
            }
        }

        onUiReady()
    }

    private fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            gamesRepository.requestPopularGames()
            _state.value = UiState(loading = false)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val games: List<Game>? = null
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val gamesRepository: GamesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(gamesRepository) as T
    }
}