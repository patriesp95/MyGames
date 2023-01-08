package com.patriciamespert.mygamesac.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Parcelize
@Entity
data class Game(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val released: String,
    val backgroundImage: String?,
    val rating: Double,
    val ratingTop: Int,
    val added: Int,
    val favorite: Boolean
) //: Parcelable