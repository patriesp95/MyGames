package com.patriciamespert.mygamesac

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.patriciamespert.mygamesac.databinding.ViewGameItemBinding

class GamesAdapter(
    var games:List<GameResult>,
    private val gameClickedListener: (GameResult) -> Unit
    ):
    RecyclerView.Adapter<GamesAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewGameItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game)
        holder.itemView.setOnClickListener{ gameClickedListener(game)}
    }

    override fun getItemCount(): Int = games.size

    class ViewHolder(private val binding: ViewGameItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(game: GameResult){
            binding.title.text = game.name
            Glide.with(binding.root.context).load(game.background_image).into(binding.cover)
        }
    }

}