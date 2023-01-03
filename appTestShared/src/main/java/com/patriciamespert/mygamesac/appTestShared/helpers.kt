package com.patriciamespert.mygamesac.appTestShared

import com.patriciamespert.mygamesac.data.GamesRepository
import com.patriciamespert.mygamesac.data.server.database.Game as DatabaseGame
import com.patriciamespert.mygamesac.data.server.GameResult as RemoteGame
import com.patriciamespert.mygamesac.data.server.database.GameDetail as DatabaseGameDetail
import com.patriciamespert.mygamesac.data.database.detail.GameDetailRoomDataSource
import com.patriciamespert.mygamesac.data.server.database.main.GameRoomDataSource
import com.patriciamespert.mygamesac.data.server.detail.GameDetailServerDataSource
import com.patriciamespert.mygamesac.data.server.main.GameServerDataSource


fun buildRepositoryWith(
    localData: List<DatabaseGame>,
    remoteData: List<RemoteGame>,
    localDetailData: List<DatabaseGameDetail>,
    remoteDetailData: List<RemoteGame>
): GamesRepository {
    val localDataSource = GameRoomDataSource(FakeGameDao(localData))
    val remoteDataSource = GameServerDataSource("1234",FakeRemoteService(remoteData))
    val localDetailDataSource = GameDetailRoomDataSource(FakeGameDetailDao(localDetailData))
    val remoteDetailDataSource = GameDetailServerDataSource("1234",FakeRemoteService(remoteDetailData))
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

fun buildRemoteGames(vararg id: Int) = id.map {
    DatabaseGameDetail(
        gameId = it,
        gameName = "Title $it",
        gameNameOriginal = "Title $it",
        gameDescription = "Title $it",
        gameBackgroundImage = "",
        gameRating = 5.0,
        gameRatingTop = 5,
        favorite = false
    )
}
