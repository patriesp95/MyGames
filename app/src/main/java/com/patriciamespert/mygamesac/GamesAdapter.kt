package com.patriciamespert.mygamesac

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patriciamespert.mygamesac.databinding.ViewGameItemBinding

class GamesAdapter(private val games:List<GameResult>): RecyclerView.Adapter<GamesAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewGameItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int = games.size

    class ViewHolder(private val binding: ViewGameItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(game: GameResult){
            binding.title.text = game.name
        }
    }

}