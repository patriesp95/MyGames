package com.patriciamespert.mygamesac.model

import com.patriciamespert.mygamesac.App
import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.GameResult
import com.patriciamespert.mygamesac.R
import com.patriciamespert.mygamesac.model.database.detail.GameDetail
import com.patriciamespert.mygamesac.model.database.main.Game
import com.patriciamespert.mygamesac.model.datasource.detail.GameDetailLocalDataSource
import com.patriciamespert.mygamesac.model.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.mygamesac.model.datasource.main.GameLocalDataSource
import com.patriciamespert.mygamesac.model.datasource.main.GameRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GamesRepository(application: App) {

    //private val apiKey = application.getString(R.string.api_key)
    private val localDataSource = GameLocalDataSource(application.db.gameDao())
    private val remoteDataSource = GameRemoteDataSource(application.getString(R.string.api_key))
    private val localGameDetailDataSource = GameDetailLocalDataSource(application.db.gameDetailDao())
    private val remoteGameDetailDataSource = GameDetailRemoteDataSource(application.getString(R.string.api_key))

    fun findGameDetails(id: Int) = remoteGameDetailDataSource.findGameDetails(id){ detailedGame ->
        if(localGameDetailDataSource.isEmpty(id)){
            localGameDetailDataSource.save(detailedGame.toLocalDetailModel())
        }
    }

    val popularGames = localDataSource.games

    suspend fun requestPopularGames() = withContext(Dispatchers.IO){
        if(localDataSource.isEmpty()){
            val retrievedGames = remoteDataSource.findPopularGames().body()?.games
            retrievedGames?.let {localDataSource.save(retrievedGames.toLocalModel())}
        }
    }
}

private fun List<GameResult>.toLocalModel(): List<Game> = map { it.toLocalModel() }


private fun GameResult.toLocalModel():Game = Game(
    id,
    name,
    released,
    background_image,
    rating,
    rating_top,
    added
    )

private fun GameDetailResponse.toLocalDetailModel(): GameDetail = GameDetail(
    gameId,
    gameName,
    gameNameOriginal,
    gameDescription,
    gameBackgroundImage,
    gameRating,
    gameRatingTop

)

