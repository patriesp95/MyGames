package com.patriciamespert.mygamesac.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.databinding.ActivityDetailBinding
import com.patriciamespert.mygamesac.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_GAME = "DetailActivity:game"
    }

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(requireNotNull(intent.getParcelableExtra(EXTRA_GAME))) }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.state.observe(this) { updateUI(it.game) }

    }

    private fun updateUI(game: GameDetailResponse) = with(binding){
        title = game.gameName
        Glide.with(this@DetailActivity)
            .load(game.gameBackgroundImage)
            .into(binding.backdrop)
        binding.description.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(game.gameDescription, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(game.gameDescription)
        }
        viewModel.bindDetailInfo(binding.detailGameInfo, game)
    }
}