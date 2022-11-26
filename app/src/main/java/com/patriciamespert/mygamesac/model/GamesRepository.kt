package com.patriciamespert.mygamesac.model

import android.util.Log
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

    private val localDataSource = GameLocalDataSource(application.db.gameDao())
    private val remoteDataSource = GameRemoteDataSource(application.getString(R.string.api_key))
    private val localGameDetailDataSource = GameDetailLocalDataSource(application.db.gameDetailDao())
    private val remoteGameDetailDataSource = GameDetailRemoteDataSource(application.getString(R.string.api_key))

    fun findById(id: Int) = localGameDetailDataSource.findById(id)

    val popularGames = localDataSource.games

    suspend fun requestDetailedGame(id: Int) = withContext(Dispatchers.IO){
        remoteGameDetailDataSource.findGameDetails(id){
            if (!localGameDetailDataSource.checkGameExists(it.gameId)) {
                localGameDetailDataSource.save(it.toLocalDetailModel())
            }
        }
    }

    suspend fun requestPopularGames() = withContext(Dispatchers.IO){
        if(localDataSource.isEmpty()){
            val retrievedGames = remoteDataSource.findPopularGames().body()?.games
            retrievedGames?.let {localDataSource.save(
                retrievedGames.map {
                    it.toLocalModel()
                }
            )}
        }
    }
}


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

