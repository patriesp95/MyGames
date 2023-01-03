package com.patriciamespert.mygamesac.data.server.main

import com.google.gson.annotations.SerializedName
import com.patriciamespert.mygamesac.data.server.GameResult

data class GameResponse(
    @SerializedName("count") var totalGames: Int,
    @SerializedName("next") var nextPage: String?=null,
    @SerializedName("previous")  var prevPage:String?=null,
    @SerializedName("results")  var games: List<GameResult>
)