package com.patriciamespert.mygamesac.ui

import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameRemoteDataSource
import com.patriciamespert.mygamesac.domain.Game
import com.patriciamespert.mygamesac.domain.GameDetail
import com.patriciamespert.mygamesac.testshared.sampleGame
import com.patriciamespert.mygamesac.testshared.sampleGameDetail
import com.patriciamespert.mygamesac.ui.detail.DetailViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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

    private lateinit var findGameFlow: MutableStateFlow<GameDetail>


    override fun checkGameExists(id: Int): Int {
        return 0
    }

    override fun save(game: GameDetail) {
        inMemoryDetailedGames.value = listOf(game)

        if(::findGameFlow.isInitialized) {
            game.gameId == findGameFlow.value.gameId

            with(game){
                this?.let {
                    findGameFlow.value = this
                }
            }
        }

    }

    override fun findById(id: Int): Flow<GameDetail> {
        findGameFlow = MutableStateFlow(inMemoryDetailedGames.value.first { it.gameId == id })
        return findGameFlow
    }

    override suspend fun update(game: GameDetail) {
        return
    }

}

class FakeRemoteDetailDataSource : GameDetailRemoteDataSource {

    var detailedGames = defaultFakeDetailedGames

    override suspend fun findGameDetails(id: Int, onComplete: (GameDetail) -> Unit) {
        return
    }
}

