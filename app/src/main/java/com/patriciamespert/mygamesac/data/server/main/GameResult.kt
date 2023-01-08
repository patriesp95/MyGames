package com.patriciamespert.mygamesac.data.server.main

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GameResult(
    val id: Int,
    val name: String,
    val released: String,
    @SerializedName("background_image")  val backgroundImage:String,
    val rating: Double,
    @SerializedName("rating_top") val ratingTop: Int,
    val added: Int,
    val favorite: Boolean,
    )
/*
//@Parcelize
    data class Rating(
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Double
) //: Parcelable

//@Parcelize
data class StatusObj(
    val owned: Int,
    val beaten: Int,
    val toplay: Int,
    val playing: Int
) //: Parcelable

//@Parcelize
data class EsrbRatingObj(
    val id: Int,
    val slug: String,
    val name: String
) //: Parcelable

//@Parcelize
data class PlatformObj(
    val platform: Platform,
    val released_at: String?=null,
    val requirements_en: RequirementObj? = null,
    val requirements_ru: RequirementObj? = null
) //: Parcelable

//@Parcelize
data class Platform(
    val id: Int,
    val slug: String,
    val name: String
) //: Parcelable

//@Parcelize
data class RequirementObj(
    val minimum: String,
    val recommended: String
) //: Parcelable*/
