package com.patriciamespert.mygamesac

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.patriciamespert.mygamesac.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_GAME = "DetailActivity:game"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val game = intent.getParcelableExtra<GameDetailResponse>(EXTRA_GAME)
        if (game != null){
            title = game.gameName
            Glide.with(this)
                .load(game.gameBackgroundImage)
                .into(binding.backdrop)
            binding.description.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(game.gameDescription, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(game.gameDescription)
            }
            bindDetailInfo(binding.detailGameInfo, game)

        }
    }

    private fun bindDetailInfo(detailInfo: TextView, game: GameDetailResponse) {
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