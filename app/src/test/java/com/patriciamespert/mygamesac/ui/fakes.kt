package com.patriciamespert.mygamesac.ui

import arrow.core.right
import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameRemoteDataSource
import com.patriciamespert.mygamesac.domain.Game
import com.patriciamespert.mygamesac.domain.GameDetail
import com.patriciamespert.mygamesac.testshared.sampleGame
import com.patriciamespert.mygamesac.testshared.sampleGameDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

val defaultFakeGames = listOf(
    sampleGame.copy(1),
    sampleGame.copy(2),
    sampleGame.copy(3),
    sampleGame.copy(4)
)

val defaultFakeDetailedGames = listOf(
    sampleGameDetail.copy(1),
    sampleGameDetail.copy(2),
    sampleGameDetail.copy(3),
    sampleGameDetail.copy(4)
)

class FakeLocalDataSource : GameLocalDataSource {

    val inMemoryGames = MutableStateFlow<List<Game>>(emptyList())

    override val games = inMemoryGames

    override fun isEmpty(): Boolean = games.value.isEmpty()

    override fun save(games: List<Game>) {
        inMemoryGames.value = games
    }

    override suspend fun updateFavorite(id: Int, favorite: Boolean) {
        return
    }
}

class FakeRemoteDataSource : GameRemoteDataSource {

    var games = defaultFakeGames

    override suspend fun findPopularGames(): List<Game>?= games
}

class FakeLocalDetailDataSource : GameDetailLocalDataSource {

    val inMemoryDetailedGames = MutableStateFlow<List<GameDetail>>(emptyList())

    val detailedGames = inMemoryDetailedGames

    override fun findById(id: Int): Flow<GameDetail> {
        TODO("Not yet implemented")
    }

    override fun checkGameExists(id: Int): Int {
        TODO("Not yet implemented")
    }

    override fun save(game: GameDetail) {
        TODO("Not yet implemented")
    }

    override suspend fun update(game: GameDetail) {
        TODO("Not yet implemented")
    }


}

class FakeRemoteDetailDataSource : GameDetailRemoteDataSource {

    var detailedGames = defaultFakeDetailedGames

    override suspend fun findGameDetails(id: Int, onComplete: (GameDetail) -> Unit) {
        TODO("Not yet implemented")
    }
}

