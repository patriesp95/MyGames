package com.patriciamespert.mygamesac

data class GameResult(
    val id: Int,
    val slug: String,
    val name: String,
    val released: String,
    val tba: Boolean,
    val backgroud_image:String,
    val rating: Double,
    val rating_top: Int,
    val ratings: List<Rating>,
    val ratings_count: Int,
    val reviews_text_count: Int,
    val added: Int,
    val added_by_status:StatusObj,
    val metacritic: Int,
    val playtime: Int,
    val suggestions_count:Int,
    val esbr_rating:EsbrRatingObj,
    val platforms:List<PlatformObj>

)

data class Rating(
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Double
)

data class StatusObj(
    val owned: Int,
    val beaten: Int,
    val toplay: Int,
    val playing: Int
)

data class EsbrRatingObj(
    val id: Int,
    val slug: String,
    val name: String
)

data class PlatformObj(
    val platform:Platform,
    val released: String,
    val requirements:RequirementObj
)

data class Platform(
    val id: Int,
    val slug: String,
    val name: String
)

data class RequirementObj(
    val minimum: String,
    val recommended: String
)