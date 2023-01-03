package com.patriciamespert.mygamesac.data

import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameRemoteDataSource
import com.patriciamespert.mygamesac.domain.GameDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GamesRepository @Inject constructor(

    private val localDataSource: GameLocalDataSource,
    private val remoteDataSource: GameRemoteDataSource,
    private val localGameDetailDataSource: GameDetailLocalDataSource,
    private val remoteGameDetailDataSource: GameDetailRemoteDataSource

){

    fun findById(id: Int) = localGameDetailDataSource.findById(id)

    val popularGames = localDataSource.games

    suspend fun requestDetailedGame(id: Int) = withContext(Dispatchers.IO){
        val remoteGameDetail = remoteGameDetailDataSource.findGameDetails(id)
        when(remoteGameDetail?.let { localGameDetailDataSource.checkGameExists(it.gameId) }) {
                0 -> localGameDetailDataSource.save(remoteGameDetail)
                1 -> print("Game ${remoteGameDetail.gameName} already exists in the local database")
                else -> print("unkown error")
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

