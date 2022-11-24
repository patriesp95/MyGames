package com.patriciamespert.mygamesac.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.patriciamespert.mygamesac.GameResult
import com.patriciamespert.mygamesac.basicDiffUtil
import com.patriciamespert.mygamesac.databinding.ViewGameItemBinding

class GamesAdapter(private val gameClickedListener: (GameResult) -> Unit):
    ListAdapter<GameResult, GamesAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewGameItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = getItem(position)
        holder.bind(game)
        holder.itemView.setOnClickListener{ gameClickedListener(game)}
    }

    class ViewHolder(private val binding: ViewGameItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(game: GameResult){
            binding.title.text = game.name
            Glide.with(binding.root.context).load(game.background_image).into(binding.cover)
        }
    }

}