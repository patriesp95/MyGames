package com.patriciamespert.mygamesac.ui.detail

import androidx.databinding.BindingAdapter
import com.patriciamespert.mygamesac.model.database.detail.GameDetail

@BindingAdapter("game")
fun GameDetailInfoView.updateMovieDetails(game: GameDetail?) {
    if (game != null) {
        setGame(game)
    }
}