package com.patriciamespert.mygamesac.ui.detail

import androidx.lifecycle.*
import com.patriciamespert.mygamesac.domain.GameDetail
import com.patriciamespert.mygamesac.usecases.FindGameUseCase
import com.patriciamespert.mygamesac.usecases.RequestGameUseCase
import com.patriciamespert.mygamesac.usecases.SwitchGameFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val gameId: Int,
    private val requestGameUseCase: RequestGameUseCase,
    private val findGameUseCase: FindGameUseCase,
    private val switchGameFavoriteUseCase: SwitchGameFavoriteUseCase

) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state

    init {
        viewModelScope.launch {
            requestGameUseCase(gameId)
            findGameUseCase(gameId)
                    .collect { _state.value = UiState(it)}
        }
        //onUiReady()
    }

    /*private fun onUiReady() {
        viewModelScope.launch {
            findGameUseCase(gameId)
                .collect { _state.value = UiState(it)}
        }
    }*/

    fun onFavoriteClicked() {
        viewModelScope.launch {
            _state.value.game?.let {switchGameFavoriteUseCase(it)}
        }
    }

    class UiState(val game: GameDetail? = null)
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val gameId: Int,
    private val requestGameUseCase: RequestGameUseCase,
    private val findGameUseCase: FindGameUseCase,
    private val switchGameFavoriteUseCase: SwitchGameFavoriteUseCase
    ) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(gameId,requestGameUseCase,findGameUseCase,switchGameFavoriteUseCase) as T
    }
}