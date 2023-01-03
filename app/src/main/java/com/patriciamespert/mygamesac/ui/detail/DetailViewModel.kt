package com.patriciamespert.mygamesac.ui.detail

import androidx.lifecycle.*
import com.patriciamespert.mygamesac.domain.GameDetail
import com.patriciamespert.mygamesac.usecases.FindGameUseCase
import com.patriciamespert.mygamesac.usecases.RequestGameUseCase
import com.patriciamespert.mygamesac.usecases.SwitchGameFavoriteUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel(
    gameId: Int,
    requestGameUseCase: RequestGameUseCase,
    findGameUseCase: FindGameUseCase,
    private val switchGameFavoriteUseCase: SwitchGameFavoriteUseCase

) : ViewModel() {

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

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory @AssistedInject constructor(
    @Assisted private val gameId: Int,
    private val requestGameUseCase: RequestGameUseCase,
    private val findGameUseCase: FindGameUseCase,
    private val switchGameFavoriteUseCase: SwitchGameFavoriteUseCase
    ) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(gameId,requestGameUseCase,findGameUseCase,switchGameFavoriteUseCase) as T
    }
}

@AssistedFactory
interface DetailViewModelAssistedFactory {
    fun create(gameId: Int) : DetailViewModelFactory
}