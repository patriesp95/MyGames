package com.patriciamespert.mygamesac.data.database.detail

import com.patriciamespert.data.datasource.detail.GameDetailLocalDataSource
import com.patriciamespert.domain.GameDetail
import com.patriciamespert.mygamesac.data.database.database.detail.GameDetailDao
import com.patriciamespert.mygamesac.data.database.GameDetail as GameDetailDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameDetailRoomDataSource @Inject constructor(private val gameDetailDao: GameDetailDao) :
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

private fun GameDetailDb.toDomainDetailModel(): GameDetail =
    GameDetail(
        gameId,
        gameName,
        gameNameOriginal,
        gameDescription,
        gameBackgroundImage,
        gameRating,
        gameRatingTop,
        favorite

    )

