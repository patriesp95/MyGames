package com.patriciamespert.mygamesac

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("count") var totalGames: Int,
    @SerializedName("next") var nextPage: Int,
    @SerializedName("previous")  var prevPage:Int,
    @SerializedName("results")  var games: List<GameResult>
)