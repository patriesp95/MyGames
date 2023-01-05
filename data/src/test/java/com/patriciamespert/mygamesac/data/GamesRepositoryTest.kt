package com.patriciamespert.mygamesac.data

import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.detail.GameDetailRemoteDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameLocalDataSource
import com.patriciamespert.mygamesac.data.datasource.main.GameRemoteDataSource
import com.patriciamespert.mygamesac.domain.Game
import com.patriciamespert.mygamesac.domain.GameDetail
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GamesRepositoryTest {

    @Mock
    lateinit var localDataSource: GameLocalDataSource

    @Mock
    lateinit var remoteDataSource: GameRemoteDataSource

    @Mock
    lateinit var localGameDetailDataSource: GameDetailLocalDataSource

    @Mock
    lateinit var remoteGameDetailDataSource: GameDetailRemoteDataSource

    private lateinit var gamesRepository: GamesRepository

    private val localGames = flowOf(listOf(sampleGame.copy(1)))

    @Before
    fun setUp() {
        whenever(localDataSource.games).thenReturn(localGames)
        gamesRepository = GamesRepository(localDataSource, remoteDataSource,localGameDetailDataSource,remoteGameDetailDataSource)
    }

    @Test
    fun `Popular games are taken from local data source if available`(): Unit = runBlocking {

        val result = gamesRepository.popularGames

        assertEquals(localGames, result)
    }

    @Test
    fun `Popular movies are saved to local data source when it's empty`(): Unit = runBlocking {
        val remoteGames = listOf(sampleGame.copy(2))
        whenever(localDataSource.isEmpty()).thenReturn(true)
        whenever(remoteDataSource.findPopularGames()).thenReturn(remoteGames)

        gamesRepository.requestPopularGames()

        verify(localDataSource).save(remoteGames)
    }

    @Test
    fun `Finding a movie by id is done in local data source`(): Unit = runBlocking {
        val gameDetail = flowOf(sampleGameDetail.copy(gameId = 5))
        whenever(localGameDetailDataSource.findById(5)).thenReturn(gameDetail)

        val result = gamesRepository.findById(5)

        assertEquals(gameDetail, result)
    }

    @Test
    fun `Switching favorite marks as favorite an unfavorite movie`(): Unit = runBlocking {
        val gameDetail = sampleGameDetail

        gamesRepository.switchFavorite(gameDetail)

        verify(localGameDetailDataSource).update(argThat { !gameDetail.favorite })
    }

    @Test
    fun `Switching favorite marks as unfavorite a favorite movie`(): Unit = runBlocking {
        val gameDetail = sampleGameDetail.copy(favorite = true)

        gamesRepository.switchFavorite(gameDetail)

        verify(localGameDetailDataSource).update(argThat { gameDetail.favorite })
    }
}

private val sampleGame = Game(
    0,
    "Title",
    "Name",
    "01/01/2025",
    5.0,
    5,
    0,
    false
)

private val sampleGameDetail = GameDetail(
    0,
    "Title",
    "Name",
    "01/01/2025",
    "",
    5.0,
    5,
    false
)