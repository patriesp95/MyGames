package com.patriciamespert.mygamesac.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.patriciamespert.mygamesac.*
import com.patriciamespert.mygamesac.core.RetrofitHelper
import com.patriciamespert.mygamesac.databinding.ActivityMainBinding
import com.patriciamespert.mygamesac.model.GamesRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val gamesRepository by lazy { GamesRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val gamesAdapter = GamesAdapter(emptyList()){ getGameDetails(it)}
        binding.recycler.adapter = gamesAdapter

        lifecycleScope.launch {
            val popularGames = gamesRepository.findPopularGames()
            val body = popularGames.body()

            runOnUiThread{
                if(body != null)
                    gamesAdapter.games = body.games
                gamesAdapter.notifyDataSetChanged()

            }

        }

    }

    private fun getGameDetails(game: GameResult) {
        val gameCall: Call<GameDetailResponse> = gamesRepository.findGameDetails(game)
        performGameInformationRetrieval(gameCall)
    }

    private fun performGameInformationRetrieval(gameCall: Call<GameDetailResponse>) {
        gameCall.enqueue(object : Callback<GameDetailResponse> {
            override fun onResponse(
                call: Call<GameDetailResponse>,
                response: Response<GameDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val game = response.body()
                    game?.let {
                        navigateTo(game)
                    }
                }
            }

            override fun onFailure(call: Call<GameDetailResponse>, t: Throwable) {
                showError()
            }

        })
    }

    private fun navigateTo(game: GameDetailResponse){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_GAME, game)
        startActivity(intent)
    }



    private fun showError(){
        Toast.makeText(this,"An error ocurred", Toast.LENGTH_SHORT)
    }
}