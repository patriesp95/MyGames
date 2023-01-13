package com.patriciamespert.mygamesac.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.sqlite.db.SupportSQLiteCompat.Api16Impl.cancel
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.doubleClick
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.patriciamespert.mygamesac.R
import com.patriciamespert.mygamesac.appTestShared.buildDatabaseGames
import com.patriciamespert.mygamesac.data.database.main.GameDao
import com.patriciamespert.mygamesac.data.datasource.main.GameRemoteDataSource
import com.patriciamespert.mygamesac.data.server.MockWebServerRule
import com.patriciamespert.mygamesac.data.server.fromJson
import com.patriciamespert.mygamesac.ui.NavHostActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
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
    lateinit var okHttpClient: OkHttpClient

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

        val resource = OkHttp3IdlingResource.create("OkHttp", okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }

    @Test
    fun click_a_game_navigates_to_detail() {
        Espresso.onView(withId(R.id.recycler))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                click()
                )
            )

        Espresso.onView(withId(R.id.game_detail_toolbar))
                .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Grand Theft Auto V"))))

    }
}