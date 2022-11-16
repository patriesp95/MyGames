package com.patriciamespert.mygamesac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.patriciamespert.mygamesac.core.RetrofitHelper
import com.patriciamespert.mygamesac.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val call = RetrofitHelper.service.listPopularVideogames(application.getString(R.string.api_key))
            val result = call.body()
            if(call.isSuccessful){
                val videogames = result?.games ?: emptyList<GameResult>()
                Log.d("Videogames", videogames.toString())
            }else{
                showError()
            }
        }

    }

    private fun showError(){
        Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_SHORT)
    }
}