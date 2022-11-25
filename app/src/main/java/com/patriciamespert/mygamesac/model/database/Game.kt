package com.patriciamespert.mygamesac.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Game (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val released: String,
    val backgroundImage:String,
    val rating: Double,
    val ratingTop: Int,
    val added: Int
    )