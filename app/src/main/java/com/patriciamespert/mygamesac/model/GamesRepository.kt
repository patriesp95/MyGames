package com.patriciamespert.mygamesac.model

import android.app.Application
import com.patriciamespert.mygamesac.App
import com.patriciamespert.mygamesac.GameDetailResponse
import com.patriciamespert.mygamesac.GameResult
import com.patriciamespert.mygamesac.R
import com.patriciamespert.mygamesac.core.RetrofitHelper
import com.patriciamespert.mygamesac.model.database.Game
import com.patriciamespert.mygamesac.model.database.GameDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Call

class GamesRepository(application: App) {

    private val apiKey = application.getString(R.string.api_key)
    private val localDataSource = GameLocalDataSource(application.db.gameDao())
    private val remoteDataSource = GameRemoteDataSource(application.getString(R.string.api_key))

    val popularGames = localDataSource.games

    suspend fun requestPopularGames() = withContext(Dispatchers.IO){
        if(localDataSource.isEmpty()){
            val retrievedGames = remoteDataSource.findPopularGames()
            retrievedGames.body()?.games?.map {it.toLocalModel()}?.let { localDataSource.save(it)}
        }
    }


    fun findGameDetails(game: Game): Call<GameDetailResponse> {
        val id = game.id.toString()
        return RetrofitHelper.service.getGameDetails(id, apiKey)
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
class GameLocalDataSource(private val gameDao: GameDao){
    val games: Flow<List<Game>> = gameDao.getAll()

    fun isEmpty(): Boolean = gameDao.gameCount() == 0

    fun save(games: List<Game>){
        gameDao.insertGames(games)
    }
}

class GameRemoteDataSource(
    private val apiKey: String
) {
    suspend fun findPopularGames() =
        RetrofitHelper.service
            .listPopularGames(
                apiKey
            )
}