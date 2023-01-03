package com.patriciamespert.mygamesac.ui.detail

import androidx.lifecycle.*
import com.patriciamespert.mygamesac.domain.GameDetail
import com.patriciamespert.mygamesac.usecases.FindGameUseCase
import com.patriciamespert.mygamesac.usecases.RequestGameUseCase
import com.patriciamespert.mygamesac.usecases.SwitchGameFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    requestGameUseCase: RequestGameUseCase,
    findGameUseCase: FindGameUseCase,
    private val switchGameFavoriteUseCase: SwitchGameFavoriteUseCase

) : ViewModel() {

    private val gameId = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle).id

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()


    init {
        viewModelScope.launch {
            requestGameUseCase(gameId)
            findGameUseCase(gameId)
                .collect { game -> _state.update { UiState(game = game) }}

        }
    }

    fun onFavoriteClicked() {
        viewModelScope.launch {
            _state.value.game?.let {game -> switchGameFavoriteUseCase(game)}
        }
    }

    data class UiState(val game: GameDetail? = null)

}