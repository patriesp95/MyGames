package com.patriciamespert.mygamesac.framework.datasource

import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailLocalDataSource
import com.patriciamespert.mygamesac.framework.database.detail.GameDetail as GameDetailDb
import com.patriciamespert.mygamesac.framework.database.detail.GameDetailDao
import com.patriciamespert.mygamesac.domain.GameDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameDetailRoomDataSource(private val gameDetailDao: GameDetailDao) :
    GameDetailLocalDataSource {
    override fun findById(id:Int):Flow<GameDetail> = gameDetailDao.findById(id).map { it.toDomainDetailModel() }

    override fun checkGameExists(id: Int): Int = gameDetailDao.checkGame(id)

    override fun save(game: GameDetail){
        gameDetailDao.insertGame(game.fromDomainModel())
    }

    override suspend fun update(game: GameDetail) {
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
        favorite

)

