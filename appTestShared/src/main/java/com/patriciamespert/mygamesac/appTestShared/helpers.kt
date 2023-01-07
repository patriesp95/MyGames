package com.patriciamespert.mygamesac.appTestShared

import com.patriciamespert.mygamesac.data.GamesRepository
import com.patriciamespert.mygamesac.data.database.Game as DatabaseGame
import com.patriciamespert.mygamesac.data.server.main.GameResult as RemoteGame
import com.patriciamespert.mygamesac.data.server.detail.GameDetailResponse as RemoteDetailGame
import com.patriciamespert.mygamesac.data.database.GameDetail as DatabaseGameDetail
import com.patriciamespert.mygamesac.data.database.detail.GameDetailRoomDataSource
import com.patriciamespert.mygamesac.data.server.database.main.GameRoomDataSource
import com.patriciamespert.mygamesac.data.server.detail.*
import com.patriciamespert.mygamesac.data.server.main.*


fun buildRepositoryWith(
    localData: List<DatabaseGame>,
    remoteData: List<RemoteGame>,
    localDetailData: List<DatabaseGameDetail>,
    remoteDetailData: List<RemoteDetailGame>
): GamesRepository {
    val localDataSource = GameRoomDataSource(FakeGameDao(localData))
    val remoteDataSource = GameServerDataSource("1234",FakeRemoteService(remoteData))
    val localDetailDataSource = GameDetailRoomDataSource(FakeGameDetailDao(localDetailData))
    val remoteDetailDataSource = GameDetailServerDataSource("1234",FakeRemoteService(remoteData))
    return GamesRepository(localDataSource, remoteDataSource,localDetailDataSource,remoteDetailDataSource)
}


fun buildDatabaseGames(vararg id: Int) = id.map {
    DatabaseGame(
        id = it,
        name = "Title $it",
        released = "01/01/2025",
        backgroundImage = "",
        rating = 5.0,
        ratingTop = 5,
        added = 0,
        favorite = false
    )
}

fun buildDatabaseDetailGames(vararg id: Int) = id.map {
    DatabaseGameDetail(
        gameId = it,
        gameName = "Title $it",
        gameNameOriginal = "Title $it",
        gameDescription = "Description $it",
        gameBackgroundImage = "",
        gameRating = 5.0,
        gameRatingTop = 5,
        favorite = false
    )
}


fun buildRemoteGames(vararg id: Int) = id.map {
    RemoteGame(
        id = it,
        slug  = "slug $it",
        name = "Title $it",
        released = "24/07/76",
        tba = false,
        background_image = "",
        rating = 5.0,
        rating_top = 5,
        ratings = emptyList(),
        ratings_count = 2,
        reviews_text_count = 2,
        added = 1,
        added_by_status = null,
        metacritic = 3,
        playtime = 5,
        suggestions_count = 5,
        esrb_rating = null,
        platforms = emptyList()
    )
}


fun buildRemoteDetailGames(vararg id: Int) = id.map {
    RemoteDetailGame(
        gameId = it,
        gameSlug = "Slug $it",
        gameName = "Name $it",
        gameNameOriginal = "Name Original $it",
        gameDescription = "Description $it",
        gameMetacritic = 2,
        gameMetacriticPlatforms = emptyList(),
        gameReleasedDate = "24/07/76",
        gameTba = false,
        gameUpdated ="y",
        gameBackgroundImage = "",
        gameBackgroundImageAditional = "",
        gameWebsite = "Website $it",
        gameRating = 5.3,
        gameRatingTop = 5,
        gameRatings = emptyList(),
        gameReactions=null,
        gameAddedDate=3,
        gameAddedByStatus=null,
        gamePlaytime = 5,
        gameScreenshotsCount = 5,
        gameMoviesCount = 5,
        creators_count = 5,
        gameAchievementsCount = 5,
        gameParentAchievementsCount = 5,
        gameRedditUrl = "Reddit url $it",
        gameRedditName = "Reddit name $it",
        gameRedditDescription = "Reddit description $it",
        gameRedditLogo = "Reddit logo $it",
        gameRedditCount = 5,
        gameTwitchCount = "Twitch count $it",
        gameYoutubeCount = "Youtube count $it",
        gameReviewsTextCount = "5",
        gameRatingsCount = 5,
        gameSuggestionsCount = "5",
        gameAlternativeNames = emptyList(),
        gameMetacriticUrl = "Metacritic url $it",
        gameParentsCount = 5,
        gameAdditionsCount = 5,
        gameSeriesCount = 5,
        gameEsrbRating = null,
        gamePlatforms = emptyList(),
        gameClip = "",
        gameDescriptionRaw = ""
    )
}
