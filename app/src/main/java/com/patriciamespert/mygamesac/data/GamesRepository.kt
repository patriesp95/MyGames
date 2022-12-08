package com.patriciamespert.mygamesac.data

import com.patriciamespert.mygamesac.App
import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.R
import com.patriciamespert.mygamesac.domain.GameDetail
import com.patriciamespert.mygamesac.framework.datasource.GameDetailServerDataSource
import com.patriciamespert.mygamesac.framework.datasource.GameDetailRoomDataSource
import com.patriciamespert.mygamesac.framework.datasource.GameRoomDataSource
import com.patriciamespert.mygamesac.framework.datasource.GameServerDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GamesRepository(application: App) {

    private val localDataSource = GameRoomDataSource(application.db.gameDao())
    private val remoteDataSource = GameServerDataSource(application.getString(R.string.api_key))
    private val localGameDetailDataSource = GameDetailRoomDataSource(application.db.gameDetailDao())
    private val remoteGameDetailDataSource = GameDetailServerDataSource(application.getString(R.string.api_key))

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

