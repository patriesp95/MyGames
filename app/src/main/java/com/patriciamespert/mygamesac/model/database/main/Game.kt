package com.patriciamespert.mygamesac.model.database.main

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Game (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val released: String,
    val backgroundImage:String,
    val rating: Double,
    val ratingTop: Int,
    val added: Int,
    val favorite: Boolean,
    ) : Parcelable