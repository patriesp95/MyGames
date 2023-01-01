package com.patriciamespert.mygamesac.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.patriciamespert.mygamesac.R
import com.patriciamespert.mygamesac.basicDiffUtil
import com.patriciamespert.mygamesac.databinding.ViewGameItemBinding
import com.patriciamespert.mygamesac.inflate
import com.patriciamespert.domain.Game

class GamesAdapter(private val gameClickedListener: (Game) -> Unit):
    ListAdapter<Game, GamesAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_game_item,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = getItem(position)
        holder.bind(game)
        holder.itemView.setOnClickListener{ gameClickedListener(game)}
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val binding = ViewGameItemBinding.bind(view)
        fun bind(game: Game) {
            binding.game = game
        }
    }

}