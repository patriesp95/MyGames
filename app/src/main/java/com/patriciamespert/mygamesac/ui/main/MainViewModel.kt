package com.patriciamespert.mygamesac.ui.main

import androidx.lifecycle.*
import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.GameResult
import com.patriciamespert.mygamesac.model.GamesRepository
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
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(games = gamesRepository.findPopularGames().body()?.games)
        }
    }

    fun onGameClicked(game: GameResult) =
        performGameInformationRetrieval(gamesRepository.findGameDetails(game))


    private fun performGameInformationRetrieval(gameCall: Call<GameDetailResponse>) {
        gameCall.enqueue(object : Callback<GameDetailResponse> {
            override fun onResponse(
                call: Call<GameDetailResponse>,
                response: Response<GameDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val game = response.body()
                    game?.let {
                        _state.value = _state.value.copy(navigateTo = game )
                    }
                }
            }

            override fun onFailure(call: Call<GameDetailResponse>, t: Throwable) {
                print("An error ocurred: ${t.message}")
            }

        })
    }

    data class UiState(
        var loading: Boolean = false,
        var games: List<GameResult>? = null,
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