package com.patriciamespert.mygamesac.data

import com.patriciamespert.mygamesac.App
import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.R
import com.patriciamespert.mygamesac.domain.GameDetail
import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameRemoteDataSource
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
        remoteGameDetailDataSource.findGameDetails(id) {
            when(localGameDetailDataSource.checkGameExists(it.gameId)) {
                0 -> localGameDetailDataSource.save(it.toDomainDetailModel())
                1 -> print("Game ${it.gameName} already exists in the local database")
                else -> print("unkown error")
            }
        }
    }

    suspend fun requestPopularGames() = withContext(Dispatchers.IO){
        if(localDataSource.isEmpty()){
            val retrievedGames = remoteDataSource.findPopularGames()
            retrievedGames?.let {localDataSource.save(it)}
        }
    }

    suspend fun switchFavorite(game: GameDetail) {
        val updatedGame = game.copy(favorite = !game.favorite)
        localGameDetailDataSource.update(updatedGame)
        localDataSource.updateFavorite(updatedGame.gameId,updatedGame.favorite)
    }
}


private fun GameDetailResponse.toDomainDetailModel(): GameDetail = GameDetail(
    gameId,
    gameName,
    gameNameOriginal,
    gameDescription,
    gameBackgroundImage,
    gameRating,
    gameRatingTop,
    false

)

