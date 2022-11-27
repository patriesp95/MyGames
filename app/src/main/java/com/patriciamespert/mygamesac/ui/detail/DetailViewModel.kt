package com.patriciamespert.mygamesac.ui.detail

import androidx.lifecycle.*
import com.patriciamespert.mygamesac.data.GamesRepository
import com.patriciamespert.mygamesac.data.database.detail.GameDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val gameId: Int,
    private val repository: GamesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state

    init {
        viewModelScope.launch {
            repository.requestDetailedGame(gameId)
        }
        onUiReady()
    }

    private fun onUiReady() {
        viewModelScope.launch {
            repository.findById(gameId).collect {
                _state.value = UiState(it)
            }
        }
    }

    fun onFavoriteClicked() {
        viewModelScope.launch {
            _state.value.game?.let {repository.switchFavorite(it)}
        }
    }

    class UiState(val game: GameDetail? = null)
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val gameId: Int, private val repository: GamesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(gameId,repository) as T
    }
}