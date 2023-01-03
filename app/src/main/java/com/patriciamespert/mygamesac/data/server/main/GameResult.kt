package com.patriciamespert.mygamesac.data.server

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val id: Int,
    val slug: String,
    val name: String,
    val released: String,
    val tba: Boolean,
    val background_image:String,
    val rating: Double,
    val rating_top: Int,
    val ratings: List<Rating>,
    val ratings_count: Int,
    val reviews_text_count: Int,
    val added: Int,
    val added_by_status: StatusObj?=null,
    val metacritic: Int,
    val playtime: Int,
    val suggestions_count:Int,
    val esrb_rating: EsrbRatingObj?=null,
    val platforms:List<PlatformObj>

): Parcelable

@Parcelize
data class Rating(
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Double
) : Parcelable

@Parcelize
data class StatusObj(
    val owned: Int,
    val beaten: Int,
    val toplay: Int,
    val playing: Int
) : Parcelable

@Parcelize
data class EsrbRatingObj(
    val id: Int,
    val slug: String,
    val name: String
) : Parcelable

@Parcelize
data class PlatformObj(
    val platform: Platform,
    val released_at: String?=null,
    val requirements_en: RequirementObj? = null,
    val requirements_ru: RequirementObj? = null
) : Parcelable

@Parcelize
data class Platform(
    val id: Int,
    val slug: String,
    val name: String
) : Parcelable

@Parcelize
data class RequirementObj(
    val minimum: String,
    val recommended: String
) : Parcelable