package com.patriciamespert.mygamesac.data.server

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameDetailResponse (
    @SerializedName("id") val gameId: Int,
    @SerializedName("slug") val gameSlug: String,
    @SerializedName("name") val gameName: String,
    @SerializedName("name_original") val gameNameOriginal: String,
    @SerializedName("description") val gameDescription: String,
    @SerializedName("metacritic") val gameMetacritic: Int,
    @SerializedName("metacritic_platforms") val gameMetacriticPlatforms: List<MetacriticPlatformObj>,
    @SerializedName("released") val gameReleasedDate: String,
    @SerializedName("tba") val gameTba: Boolean,
    @SerializedName("updated") val gameUpdated: String,
    @SerializedName("background_image") val gameBackgroundImage: String,
    @SerializedName("background_image_additional") val gameBackgroundImageAditional: String,
    @SerializedName("website") val gameWebsite: String,
    @SerializedName("rating") val gameRating: Double,
    @SerializedName("rating_top") val gameRatingTop: Int,
    @SerializedName("ratings") val gameRatings:List<RatingDetailObj>,
    @Expose
    @SerializedName("reactions") val gameReactions:ReactionDetailObj?=null,
    @SerializedName("added") val gameAddedDate: Int,
    @SerializedName("added_by_status") val gameAddedByStatus: StatusDetailObj,
    @SerializedName("playtime") val gamePlaytime: Int,
    @SerializedName("screenshots_count") val gameScreenshotsCount: Int,
    @SerializedName("movies_count") val gameMoviesCount: Int,
    @SerializedName("creators_count") val creators_count: Int,
    @SerializedName("achievements_count") val gameAchievementsCount: Int,
    @SerializedName("parent_achievements_count") val gameParentAchievementsCount: Int,
    @SerializedName("reddit_url") val gameRedditUrl: String,
    @SerializedName("reddit_name") val gameRedditName: String,
    @SerializedName("reddit_description") val gameRedditDescription: String,
    @SerializedName("reddit_logo") val gameRedditLogo: String,
    @SerializedName("reddit_count") val gameRedditCount: Integer,
    @SerializedName("twitch_count") val gameTwitchCount: String,
    @SerializedName("youtube_count") val gameYoutubeCount:String,
    @SerializedName("reviews_text_count") val gameReviewsTextCount: String,
    @SerializedName("ratings_count") val gameRatingsCount: Int,
    @SerializedName("suggestions_count") val gameSuggestionsCount: String,
    @SerializedName("alternative_names") val gameAlternativeNames: List<String>,
    @SerializedName("metacritic_url") val gameMetacriticUrl: String,
    @SerializedName("parents_count") val gameParentsCount: Int,
    @SerializedName("additions_count") val gameAdditionsCount: Int,
    @SerializedName("game_series_count") val gameSeriesCount: Int,
    @SerializedName("esrb_rating") val gameEsrbRating: EsrbRatingDetailObj?=null,
    @SerializedName("platforms") val gamePlatforms:List<PlatformDetailObj>,
    @SerializedName("clip") val gameClip: String?=null,
    @SerializedName("description_raw") val gameDescriptionRaw: String
) : Parcelable

@Parcelize
data class MetacriticPlatformObj(
    @SerializedName("metascore") val gameMetascore: Int,
    @SerializedName("url") val gameUrl: String
) : Parcelable

@Parcelize
data class RatingDetailObj(
    @SerializedName("id") val gameRatingId: Int,
    @SerializedName("title") val gameRatingTitle: String,
    @SerializedName("count") val gameRatingCount: Int,
    @SerializedName("percent") val gameRatingPercent: Double
) : Parcelable

@Parcelize
data class ReactionDetailObj(
    @Expose
    @SerializedName("1") val one: Int?=null,
) : Parcelable

@Parcelize
data class StatusDetailObj(
    @SerializedName("owned") val gameStatusOwned: Int,
    @SerializedName("beaten") val gameStatusBeaten: Int,
    @SerializedName("toplay") val gameStatusToplay: Int,
    @SerializedName("playing") val gameStatusPlaying: Int
) : Parcelable

@Parcelize
data class EsrbRatingDetailObj(
    @SerializedName("id") val gameEsrbRatingId: Int,
    @SerializedName("slug") val gameEsrbRatingSlug: String,
    @SerializedName("name") val gameEsrbRatingName: String
) : Parcelable

@Parcelize
data class PlatformDetailObj(
    @SerializedName("platform") val gamePlatform:PlatformDetail?=null,
    @SerializedName("released_at") val gamePlatformReleased: String?=null,
    @SerializedName("requirements") val gamePlatformRequirements:RequirementDetailObj?=null
) : Parcelable

@Parcelize
data class PlatformDetail(
    @SerializedName("id") val gamePlatformId: Int,
    @SerializedName("slug") val gamePlatformSlug: String,
    @SerializedName("name") val gamePlatformName: String,
    @SerializedName("image") val gamePlatformImage: String?=null,
    @SerializedName("year_end") val gamePlatformYearEnd: Int?=null,
    @SerializedName("year_start") val gamePlatformYearStart: Int?=null,
    @SerializedName("games_count") val gamePlatformGamesCount: Int,
    @SerializedName("image_background") val gamePlatformImageBackground: String

) : Parcelable

@Parcelize
data class RequirementDetailObj(
    @SerializedName("minimum") val gameMinimumRequirement: String?=null,
    @SerializedName("recommended") val gameRecommendedRequirement: String?=null
) : Parcelable