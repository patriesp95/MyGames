package com.patriciamespert.mygamesac

import com.google.gson.annotations.SerializedName

data class GameDetailResponse (
    val id: Int,
    val slug: String,
    val name: String,
    val name_original: String,
    val description: String,
    val metacritic: Int,
    val metacritic_platforms: List<MetacriticPlatformObj>,
    val released: String,
    val tba: Boolean,
    val updated: String,
    val background_image: String,
    val background_image_aditional: String,
    val website: String,
    val rating: Double,
    val rating_top: Int,
    val ratings:List<RatingDetailObj>,
    val reactions: List<ReactionDetailObj>,
    val added: Int,
    val added_by_status: StatusDetailObj,
    val playtime: Int,
    val screenshots_count: Int,
    val movies_count: Int,
    val creators_count: Int,
    val achievements_count: Int,
    val parent_achievements_count: Int,
    val reddit_url: String,
    val reddit_name: String,
    val reddit_description: String,
    val reddit_logo: String,
    val reddit_count: Integer,
    val twitch_count: String,
    val youtube_count:String,
    val reviews_text_count: String,
    val ratings_count: Int,
    val suggestions_count: String,
    val alternative_names: List<String>,
    val metacritic_url: String,
    val parents_count: Int,
    val additions_count: Int,
    val game_series_count: Int,
    val esrb_rating: EsrbRatingDetailObj,
    val platforms:List<PlatformDetailObj>
    )

data class MetacriticPlatformObj(
    val metascore: Int,
    val url: String
)

data class RatingDetailObj(
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Double
)

data class ReactionDetailObj(
    @SerializedName("1") val one: Int,
    @SerializedName("2") val two: Int,
    @SerializedName("3") val three: Int,
    @SerializedName("4") val four: Int,
    @SerializedName("5") val five: Int,
    @SerializedName("6") val six: Int,
    @SerializedName("7") val seven: Int,
    @SerializedName("8") val eight: Int,
    @SerializedName("9") val nine: Int,
    @SerializedName("10") val ten: Int,
    @SerializedName("11") val eleven: Int,
    @SerializedName("12") val twelve: Int,
    @SerializedName("13") val thirteen: Int,
    @SerializedName("14") val fourteen: Int,
    @SerializedName("15") val fifteen: Int,
    @SerializedName("16") val sixteen: Int,
    @SerializedName("17") val seventeen: Int,
    @SerializedName("18") val eighteen: Int,
    @SerializedName("19") val nineteen: Int,
    @SerializedName("20") val twenty: Int,
    @SerializedName("20") val twentyone: Int,

    )

data class StatusDetailObj(
    val owned: Int,
    val beaten: Int,
    val toplay: Int,
    val playing: Int
)

data class EsrbRatingDetailObj(
    val id: Int,
    val slug: String,
    val name: String
)

data class PlatformDetailObj(
    val platform:PlatformDetail,
    val released: String,
    val requirements:RequirementDetailObj
)


data class PlatformDetail(
    val id: Int,
    val slug: String,
    val name: String
)

data class RequirementDetailObj(
    val minimum: String,
    val recommended: String
)