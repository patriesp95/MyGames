package com.patriciamespert.mygamesac.data

import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.domain.GameDetail
import com.patriciamespert.mygamesac.framework.database.detail.GameDetailRoomDataSource
import com.patriciamespert.mygamesac.framework.database.main.GameRoomDataSource
import com.patriciamespert.mygamesac.framework.server.detail.GameDetailServerDataSource
import com.patriciamespert.mygamesac.framework.server.main.GameServerDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GamesRepository(
    private val localDataSource: GameRoomDataSource,
    private val remoteDataSource: GameServerDataSource,
    private val localGameDetailDataSource: GameDetailRoomDataSource,
    private val remoteGameDetailDataSource: GameDetailServerDataSource
) {

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

