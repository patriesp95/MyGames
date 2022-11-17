package com.patriciamespert.mygamesac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            /*title = game.name*/
            Glide.with(this)
                .load(game.gameBackgroundImage)
                .into(binding.backdrop)

        }
    }
}