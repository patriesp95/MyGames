package com.patriciamespert.mygamesac.ui.detail

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.patriciamespert.mygamesac.data.database.detail.GameDetail

class GameDetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setGame(game: GameDetail) = game.apply {
        text = buildSpannedString {

            bold { append("Original title: ") }
            appendLine(gameNameOriginal)

            bold { append("Rating: ") }
            appendLine(gameRating.toString())

            bold { append("Top Rating: ") }
            append(gameRatingTop.toString())
        }
    }
}