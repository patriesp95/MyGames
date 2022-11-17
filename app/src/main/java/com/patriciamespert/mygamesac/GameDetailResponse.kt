package com.patriciamespert.mygamesac

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GameDetailResponse (
    @Expose
    @SerializedName("id") val gameId: Int,

    @Expose
    @SerializedName("slug") val gameSlug: String,

    @Expose
    @SerializedName("name") val gameName: String,

    @SerializedName("name_original") val gameNameOriginal: String,
    @SerializedName("description") val gameDescription: String,

    @Expose
    @SerializedName("metacritic") val gameMetacritic: Int,

    @SerializedName("metacritic_platforms") val gameMetacriticPlatforms: List<MetacriticPlatformObj>,

    @Expose
    @SerializedName("released") val gameReleasedDate: String,

    @Expose
    @SerializedName("tba") val gameTba: Boolean,
    @SerializedName("updated") val gameUpdated: String,

    @Expose
    @SerializedName("background_image") val gameBackgroundImage: String,
    @SerializedName("background_image_additional") val gameBackgroundImageAditional: String,
    @SerializedName("website") val gameWebsite: String,

    @Expose
    @SerializedName("rating") val gameRating: Double,

    @Expose
    @SerializedName("rating_top") val gameRatingTop: Int,

    @Expose
    @SerializedName("ratings") val gameRatings:List<RatingDetailObj>,

    @SerializedName("reactions") val gameReactions:ReactionDetailObj?=null,

    @Expose
    @SerializedName("added") val gameAddedDate: Int,
    @Expose
    @SerializedName("added_by_status") val gameAddedByStatus: StatusDetailObj,
    @Expose
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

    @Expose
    @SerializedName("esrb_rating") val gameEsrbRating: EsrbRatingDetailObj,
    @Expose
    @SerializedName("platforms") val gamePlatforms:List<PlatformDetailObj>
    )

data class MetacriticPlatformObj(
    @SerializedName("metascore") val gameMetascore: Int,
    @SerializedName("url") val gameUrl: String
)

data class RatingDetailObj(
    @Expose
    @SerializedName("id") val gameRatingId: Int,
    @Expose
    @SerializedName("title") val gameRatingTitle: String,
    @Expose
    @SerializedName("count") val gameRatingCount: Int,
    @Expose
    @SerializedName("percent") val gameRatingPercent: Double
)

data class ReactionDetailObj(
    @Expose
    @SerializedName("1") val one: Int?=null,


    )

data class StatusDetailObj(
    @Expose
    @SerializedName("owned") val gameStatusOwned: Int,
    @Expose
    @SerializedName("beaten") val gameStatusBeaten: Int,
    @Expose
    @SerializedName("toplay") val gameStatusToplay: Int,
    @Expose
    @SerializedName("playing") val gameStatusPlaying: Int
)

data class EsrbRatingDetailObj(
    @Expose
    @SerializedName("id") val gameEsrbRatingId: Int,
    @Expose
    @SerializedName("slug") val gameEsrbRatingSlug: String,
    @Expose
    @SerializedName("name") val gameEsrbRatingName: String
)

data class PlatformDetailObj(
    @Expose
    @SerializedName("platform") val gamePlatform:PlatformDetail,
    @Expose
    @SerializedName("released") val gamePlatformReleased: String,
    @Expose
    @SerializedName("requirements") val gamePlatformRequirements:RequirementDetailObj
)


data class PlatformDetail(
    @Expose
    @SerializedName("id") val gamePlatformId: Int,
    @Expose
    @SerializedName("slug") val gamePlatformSlug: String,
    @Expose
    @SerializedName("name") val gamePlatformName: String
)

data class RequirementDetailObj(
    @Expose
    @SerializedName("minimum") val gameMinimumRequirement: String,
    @Expose
    @SerializedName("recommended") val gameRecommendedRequirement: String
)