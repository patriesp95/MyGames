package com.patriciamespert.mygamesac.ui.detail

import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.lifecycle.*
import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.GameResult
import com.patriciamespert.mygamesac.model.GamesRepository
import com.patriciamespert.mygamesac.model.database.detail.GameDetail
import com.patriciamespert.mygamesac.ui.main.MainViewModel
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
    }

    fun onUiReady() {
        viewModelScope.launch {
            repository.findById(gameId).collect {
                _state.value = UiState(it)
            }
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