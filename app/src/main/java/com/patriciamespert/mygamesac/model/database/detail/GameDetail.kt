package com.patriciamespert.mygamesac.model.database.detail

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class GameDetail (
    @PrimaryKey(autoGenerate = true) val gameId: Int,
    val gameName: String,
    val gameNameOriginal: String,
    val gameDescription: String,
    val gameBackgroundImage: String,
    val gameRating: Double,
    val gameRatingTop: Int,

    ) : Parcelable