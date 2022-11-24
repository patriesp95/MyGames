package com.patriciamespert.mygamesac.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.patriciamespert.mygamesac.*
import com.patriciamespert.mygamesac.databinding.ActivityMainBinding
import com.patriciamespert.mygamesac.model.GamesRepository
import com.patriciamespert.mygamesac.ui.detail.DetailActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(GamesRepository(this)) }

    private val adapter = GamesAdapter { viewModel.onGameClicked(it) }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycler.adapter = adapter

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect(::updateUI)
            }
        }

    }

    private fun updateUI(state: MainViewModel.UiState) {
        binding.progress.visible = state.loading
        state.games?.let(adapter::submitList)
        state.navigateTo?.let(::navigateTo)
    }


    private fun navigateTo(game: GameDetailResponse){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_GAME, game)
        startActivity(intent)
    }

}