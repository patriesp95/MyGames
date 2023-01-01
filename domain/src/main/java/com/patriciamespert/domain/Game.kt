package com.patriciamespert.domain

data class Game (
    val id: Int,
    val name: String,
    val released: String,
    val backgroundImage:String,
    val rating: Double,
    val ratingTop: Int,
    val added: Int,
    val favorite: Boolean
    )