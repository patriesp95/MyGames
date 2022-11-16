package com.patriciamespert.mygamesac

data class GameResult(
    val added: Int,
    val added_by_status: AddedByStatus,
    val background_image: String,
    val esrb_rating: EsrbRating,
    val id: Int,
    val metacritic: Int,
    val name: String,
    val platforms: List<Platform>,
    val playtime: Int,
    val rating: Int,
    val rating_top: Int,
    val ratings: Ratings,
    val ratings_count: Int,
    val released: String,
    val reviews_text_count: String,
    val slug: String,
    val suggestions_count: Int,
    val tba: Boolean,
    val updated: String
)

class AddedByStatus

data class EsrbRating(
    val id: Int,
    val name: String,
    val slug: String
)

data class Platform(
    val platform: PlatformX,
    val released_at: String,
    val requirements: Requirements
)

class Ratings

data class PlatformX(
    val id: Int,
    val name: String,
    val slug: String
)

data class Requirements(
    val minimum: String,
    val recommended: String
)