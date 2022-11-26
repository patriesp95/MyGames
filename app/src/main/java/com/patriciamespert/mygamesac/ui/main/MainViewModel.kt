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
            _state.value = UiState(loading = true)
            gamesRepository.requestPopularGames()
        }
    }

    fun onGameClicked(game: Game) =
        performGameInformationRetrieval(gamesRepository.findGameDetails(game.id)){
            _state.value = _state.value.copy(navigateTo = it )
        }


    private fun performGameInformationRetrieval(gameCall: Call<GameDetailResponse>,onComplete: (GameDetailResponse) -> Unit) {
        gameCall.enqueue(object : Callback<GameDetailResponse> {
            override fun onResponse(
                call: Call<GameDetailResponse>,
                response: Response<GameDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val game = response.body()
                    game?.let {
                        onComplete.invoke(game)
                    }
                }
            }

            override fun onFailure(call: Call<GameDetailResponse>, t: Throwable) {
                print("An error ocurred: ${t.message}")
            }

        })
    }

    fun onNavigationDone() {
        _state.value = _state.value.copy(navigateTo = null)
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