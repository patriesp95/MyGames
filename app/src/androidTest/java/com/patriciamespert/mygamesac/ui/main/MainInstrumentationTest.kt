package com.patriciamespert.mygamesac.ui.main

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.patriciamespert.mygamesac.appTestShared.buildDatabaseGames
import com.patriciamespert.mygamesac.data.database.main.GameDao
import com.patriciamespert.mygamesac.ui.NavHostActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
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

    @get:Rule(order = 2)
    val activityRule = ActivityScenarioRule(NavHostActivity::class.java)

    @Inject
    lateinit var gameDao: GameDao

    @Before
    fun setUp() {
        hiltRule.inject()
    }


    @Test
    fun check_6_items_db() = runTest {
        gameDao.insertGames(buildDatabaseGames(1, 2, 3, 4, 5, 6))
        Assert.assertEquals(6, gameDao.gameCount())
    }

    @Test
    fun check_4_items_db() = runTest {
        gameDao.insertGames(buildDatabaseGames(1, 2, 3, 4))
        Assert.assertEquals(4, gameDao.gameCount())
    }
}