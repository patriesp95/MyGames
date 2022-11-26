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
    gameId: Int,
    private val repository: GamesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state

    class UiState(val game: GameDetail? = null)


    fun bindDetailInfo(detailInfo: TextView, game: GameDetail?) {
        detailInfo.text = buildSpannedString {

            bold { append("Original title: ") }
            appendLine(game?.gameNameOriginal)

            bold { append("Rating: ") }
            appendLine(game?.gameRating.toString())

            bold { append("Top Rating: ") }
            appendLine(game?.gameRatingTop.toString())
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val gameId: Int, private val repository: GamesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(gameId,repository) as T
    }
}