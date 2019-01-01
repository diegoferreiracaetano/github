package com.diegoferreiracaetano.github.ui

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.diegoferreiracaetano.github.R
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.test.KoinTest
import com.diegoferreiracaetano.data.di.testModule

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest : KoinTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    lateinit var server: MockWebServer

    @Before
    @Throws(Exception::class)
    fun setUp() {
        server = MockWebServer()
        loadKoinModules(testModule(server.url("/").toString()))
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        server.shutdown()
    }


    @Test
    fun GivenRepositoryScreen_WhenInicialize_ShouldRepositoriesTitle() {

        server.setDispatcher(MockServerDispatcher().RequestDispatcher())
        activityTestRule.launchActivity(Intent())

        onView(withText("Github")).check(matches(isDisplayed()))
    }

    @Test
    fun GivenRepositoryScreen_WhenLoadDataRepositories_ShouldRepositoryItens() {

        server.setDispatcher(MockServerDispatcher().RequestDispatcher())
        activityTestRule.launchActivity(Intent())

        onView(allOf(withId(R.id.owner_name), withText("iluwatar"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.owner_image), hasSibling(withText("iluwatar")))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.pull_title), withText("java-design-patterns"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.pull_body), withText("Design patterns implemented in Java"))).check(matches(isDisplayed()))
    }

    @Test
    fun GivenRepositoryListingScreen_WhenClickFirstItem_ShouldPullTitle() {

        server.setDispatcher(MockServerDispatcher().RequestDispatcher())
        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withText("Pull Request")).check(matches(isDisplayed()))
    }

    @Test
    fun GivenPullScreen_WhenLoadDataPull_ShouldPullItens() {

        server.setDispatcher(MockServerDispatcher().RequestDispatcher())
        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(allOf(withId(R.id.pull_title), withText("bugfix: build failure"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.pull_body), withText("Fix issue #834"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.owner_name), withText("kezhenxu94"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.owner_image), hasSibling(withText("kezhenxu94")))).check(matches(isDisplayed()))
    }
}