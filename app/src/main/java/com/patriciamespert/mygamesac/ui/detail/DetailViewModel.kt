package com.patriciamespert.mygamesac.ui.detail

import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.lifecycle.*
import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.GameResult
import com.patriciamespert.mygamesac.ui.main.MainViewModel
import kotlinx.coroutines.launch

class DetailViewModel(game: GameDetailResponse) : ViewModel() {

    class UiState(val game: GameDetailResponse)

    private val _state = MutableLiveData(UiState(game))
    val state: LiveData<UiState> get() = _state


    fun bindDetailInfo(detailInfo: TextView, game: GameDetailResponse) {
        detailInfo.text = buildSpannedString {

            bold { append("Original title: ") }
            appendLine(game.gameNameOriginal)

            bold { append("Release date: ") }
            appendLine(game.gameReleasedDate)

            bold { append("Rating: ") }
            appendLine(game.gameRating.toString())

            bold { append("Top Rating: ") }
            appendLine(game.gameRatingTop.toString())
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val game: GameDetailResponse) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(game) as T
    }
}