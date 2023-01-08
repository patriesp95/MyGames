package com.patriciamespert.mygamesac.ui.main

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import arrow.core.fold
import com.patriciamespert.mygamesac.appTestShared.buildDatabaseGames
import com.patriciamespert.mygamesac.data.database.main.GameDao
import com.patriciamespert.mygamesac.data.datasource.main.GameRemoteDataSource
import com.patriciamespert.mygamesac.data.server.MockWebServerRule
import com.patriciamespert.mygamesac.data.server.fromJson
import com.patriciamespert.mygamesac.ui.NavHostActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class MainInstrumentationTest {


    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 3)
    val activityRule = ActivityScenarioRule(NavHostActivity::class.java)

    @Inject
    lateinit var gameDao: GameDao

    @Inject
    lateinit var remoteDataSource: GameRemoteDataSource

    @Before
    fun setUp() {
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("popular_games.json")
        )

        hiltRule.inject()
    }

    @Test
    fun test_it_works() {

    }

    @Test
    fun check_4_items_db() = runTest {
        gameDao.insertGames(buildDatabaseGames(1, 2, 3, 4))
        Assert.assertEquals(4, gameDao.gameCount())
    }

    @Test
    fun check_6_items_db() = runTest {
        gameDao.insertGames(buildDatabaseGames(1, 2, 3, 4, 5, 6))
        Assert.assertEquals(6, gameDao.gameCount())
    }

    @Test
    fun check_mock_server_is_working() = runTest {
        val games = remoteDataSource.findPopularGames()
        games?.let{
            Assert.assertEquals("Portal", it[0].name)
        }
    }
}