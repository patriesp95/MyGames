package com.patriciamespert.mygamesac.data.datasource.detail

import com.patriciamespert.mygamesac.data.database.detail.GameDetail as GameDetailDb
import com.patriciamespert.mygamesac.data.database.detail.GameDetailDao
import com.patriciamespert.mygamesac.domain.GameDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameDetailLocalDataSource(private val gameDetailDao: GameDetailDao){
    fun findById(id:Int):Flow<GameDetail> = gameDetailDao.findById(id).map { it.toDomainDetailModel() }

    fun checkGameExists(id: Int): Int = gameDetailDao.checkGame(id)

    fun save(game: GameDetail){
        gameDetailDao.insertGame(game.fromDomainModel())
    }

    suspend fun update(game: GameDetail) {
        gameDetailDao.updateGame(listOf(game).fromDomainModel())
    }

}

private fun List<GameDetail>.fromDomainModel() = map { it.fromDomainModel() }


private fun GameDetail.fromDomainModel(): GameDetailDb = GameDetailDb(

        gameId,
        gameName,
        gameNameOriginal,
        gameDescription,
        gameBackgroundImage,
        gameRating,
        gameRatingTop,
        favorite
)

private fun GameDetailDb.toDomainDetailModel(): GameDetail = GameDetail(
        gameId,
        gameName,
        gameNameOriginal,
        gameDescription,
        gameBackgroundImage,
        gameRating,
        gameRatingTop,
        false

)

