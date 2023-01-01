package com.patriciamespert.mygamesac.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.patriciamespert.domain.Game

@BindingAdapter("items")
fun RecyclerView.setItems(games: List<Game>?) {
    if(games != null){
        (adapter as? GamesAdapter)?.submitList(games)
    }
}