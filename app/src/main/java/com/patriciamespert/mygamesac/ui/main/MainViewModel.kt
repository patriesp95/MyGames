package com.patriciamespert.mygamesac.ui.main

import androidx.lifecycle.*
import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.model.GamesRepository
import com.patriciamespert.mygamesac.model.database.main.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val gamesRepository: GamesRepository): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            gamesRepository.popularGames.collect{ games ->
                _state.value = UiState(games =  games)
            }
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            gamesRepository.requestPopularGames()
        }
    }

    data class UiState(
        var loading: Boolean = false,
        var games: List<Game>? = null,
        val navigateTo: GameDetailResponse? = null
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val gamesRepository: GamesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(gamesRepository) as T
    }
}