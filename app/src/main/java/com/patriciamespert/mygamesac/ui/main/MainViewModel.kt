package com.patriciamespert.mygamesac.ui.main

import androidx.lifecycle.*
import com.patriciamespert.mygamesac.domain.Game
import com.patriciamespert.mygamesac.usecases.GetPopularGamesUseCase
import com.patriciamespert.mygamesac.usecases.RequestPopularGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
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