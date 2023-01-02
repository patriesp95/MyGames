package com.patriciamespert.mygamesac.ui.detail

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.patriciamespert.mygamesac.domain.GameDetail

@BindingAdapter("game")
fun GameDetailInfoView.updateGameDetails(game: GameDetail?) {
    if (game != null) {
        setGame(game)
    }
}

@BindingAdapter("description")
fun TextView.toHtml(description: String ){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(description)
    }
}