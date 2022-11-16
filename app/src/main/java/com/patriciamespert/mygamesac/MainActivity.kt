package com.patriciamespert.mygamesac

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.patriciamespert.mygamesac.core.RetrofitHelper
import com.patriciamespert.mygamesac.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val gamesAdapter = GamesAdapter(emptyList()){ navigateTo(it)}
        binding.recycler.adapter = gamesAdapter

        lifecycleScope.launch {
            val apiKey = getString(R.string.api_key)
            val popularVideogames = RetrofitHelper.service.listPopularVideogames(apiKey)
            val body = popularVideogames.body()

            runOnUiThread{
                if(body != null)
                    gamesAdapter.games = body.games
                gamesAdapter.notifyDataSetChanged()

            }

        }

    }

    private fun navigateTo(game: GameResult) {
        /*val game = RetrofitHelper.service.getGameDetails(id,getString(R.string.api_key))
        val detailbody = game.body()
        if(detailbody != null)
            Log.d("patriiii",detailbody.description)*/

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_GAME, game)
        startActivity(intent)

        /*lifecycleScope.launch {
            val game = RetrofitHelper.service.getGameDetails(id,getString(R.string.api_key))
            val detailbody = game.body()
            runOnUiThread{
                if(detailbody != null)
                    Log.d("patriiii",detailbody.description)

            }

        }*/

      /*  runOnUiThread{
            val game = RetrofitHelper.service.getGameDetails(id,getString(R.string.api_key))
            val detailbody = game.body()
            if(detailbody != null)
                Log.d("patriiii",detailbody.description)

        }*/
    }

    private fun showError(){
        Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_SHORT)
    }
}