package com.patriciamespert.mygamesac.data.database.detail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameDetail (
    @PrimaryKey(autoGenerate = true)
    val gameId: Int,
    val gameName: String,
    val gameNameOriginal: String,
    val gameDescription: String,
    val gameBackgroundImage: String,
    val gameRating: Double,
    val gameRatingTop: Int,
    val favorite: Boolean
    )