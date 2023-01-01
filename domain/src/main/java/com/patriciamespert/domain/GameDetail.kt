package com.patriciamespert.domain

data class GameDetail (
    val gameId: Int,
    val gameName: String,
    val gameNameOriginal: String,
    val gameDescription: String,
    val gameBackgroundImage: String,
    val gameRating: Double,
    val gameRatingTop: Int,
    val favorite: Boolean
    )