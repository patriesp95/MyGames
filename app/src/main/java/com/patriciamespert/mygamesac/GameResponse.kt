package com.patriciamespert.mygamesac

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("count") var totalGames: Int,
    @SerializedName("next") var nextPage: String,
    @SerializedName("previous")  var prevPage:String,
    @SerializedName("results")  var games: List<GameResult>
)