package com.patriciamespert.mygamesac.appTestShared

import com.patriciamespert.mygamesac.data.server.database.detail.GameDetailDao
import com.patriciamespert.mygamesac.data.database.main.GameDao
import com.patriciamespert.mygamesac.data.server.main.GameResult as RemoteGame
import kotlinx.coroutines.flow.*
import com.patriciamespert.mygamesac.data.database.Game as DatabaseGame
import com.patriciamespert.mygamesac.data.database.GameDetail as DatabaseDetailGame
import com.patriciamespert.mygamesac.data.server.main.GameResponse as RemoteResult
import com.patriciamespert.mygamesac.data.core.ApiService as RemoteService

class FakeGameDao(games: List<DatabaseGame> = emptyList()) : GameDao {

    private val inMemoryGames = MutableStateFlow(games)
    private lateinit var findGameFlow: MutableStateFlow<DatabaseGame>

    override fun getAll(): Flow<List<DatabaseGame>> = inMemoryGames

    override fun gameCount(): Int = inMemoryGames.value.size

    override fun insertGames(games: List<DatabaseGame>) {
        inMemoryGames.value = games

        if (::findGameFlow.isInitialized) {
            games.firstOrNull() { it.id == findGameFlow.value.id }
                ?.let { findGameFlow.value = it }
        }
    }

    override fun updateGame(game: DatabaseGame): Int {
        inMemoryGames.value = listOf(game)

        if (::findGameFlow.isInitialized) {
            listOf(game) .firstOrNull() { it.id == findGameFlow.value.id }
                ?.let {
                    findGameFlow.value = it
                    return 1

                }
        }

        return 0
    }

    override suspend fun updateFavorite(id: Int, favorite: Boolean): Int {
        TODO("Not yet implemented")
    }

}


class FakeRemoteService(private val games: List<RemoteGame> = emptyList()) : RemoteService {

    /*override suspend fun listPopularGames(apiKey: String, ordering: String, platforms_count:Int): RemoteResult = RemoteResult(
        games.size,
        "2",
        "",
        games
    )

    override suspend fun getGameDetails(
        id: String,
        key: String
    ): com.patriciamespert.mygamesac.data.server.detail.GameDetailResponse {
        TODO("Not yet implemented")
    }
*/
    /*override suspend fun getGameDetails(id: String, key: String): RemoteDetailResult = RemoteDetailResult (
        0,
        "",
        "",
        "",
        "",
        0,
        emptyList(),
        "",
        false,
        "",
        "",
        "",
        "",
        0.0,
        0,
        emptyList(),
       null,
        0,
        null,
        0,
        0,
        0,
        0,
        0,
    0,
        "",
        "",
        "",
        "",
        0,
        "",
        "",
        "",
        0,
        "",
        emptyList<String>(),
        "",
        0,
        0,
        0,
        null,
        null,
        "",
        ""
    )*/
    override suspend fun listPopularGames(key: String): RemoteResult = RemoteResult(
    games.size,
    "2",
    "",
    games
    )

    override suspend fun getGameDetails(
        id: String,
        key: String
    ): com.patriciamespert.mygamesac.data.server.detail.GameDetailResponse {
        TODO("Not yet implemented")
    }


}


class FakeGameDetailDao(detailGames: List<DatabaseDetailGame> = emptyList()) : GameDetailDao {

    private val inMemoryDetailGames = MutableStateFlow(detailGames)
    private lateinit var findGameDetailFlow: MutableStateFlow<DatabaseDetailGame>

    override fun findById(id: Int): Flow<DatabaseDetailGame> {
        findGameDetailFlow = MutableStateFlow(inMemoryDetailGames.value.first { it.gameId == id })
        return findGameDetailFlow
    }

    override fun checkGame(id: Int): Int {
        findGameDetailFlow = MutableStateFlow(inMemoryDetailGames.value.first { it.gameId == id })
        return when(findGameDetailFlow){
            findGameDetailFlow.onEmpty{} -> 0
            else -> 0
        }
    }

    override fun insertGame(game: DatabaseDetailGame) {
        inMemoryDetailGames.value = listOf(game)

        if (::findGameDetailFlow.isInitialized) {
            listOf(game).firstOrNull() { it.gameId == findGameDetailFlow.value.gameId }
                ?.let { findGameDetailFlow.value = it }
        }
    }

    override suspend fun updateGame(games: List<DatabaseDetailGame>): Int {
        inMemoryDetailGames.value = games

        if (::findGameDetailFlow.isInitialized) {
            games.firstOrNull() { it.gameId == findGameDetailFlow.value.gameId }
                ?.let {
                    findGameDetailFlow.value = it
                    return 1
                }
        }
        return 0
    }

}

