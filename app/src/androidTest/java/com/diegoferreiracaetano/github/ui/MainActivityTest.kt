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
import mock.MocksTest
import mock.RepoJson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.test.KoinTest
import testModule

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest : KoinTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    lateinit var server: MockWebServer

    @Before
    @Throws(Exception::class)
    fun setUp() {
        server = MockWebServer()
        server.start()
        loadKoinModules(testModule(server.url("/").toString()))
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun givenSomethin_whenAnAction_shouldDoSomething() {

        server.enqueue(MockResponse().setResponseCode(200).setBody(RepoJson.REPO_SUCCESS))

        activityTestRule.launchActivity(Intent())

        onView(withText("Github")).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.owner_name), withText("Diego"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.owner_image), hasSibling(withText("Diego")))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.pull_title), withText("java-design-patterns"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.pull_body), withText("Design patterns implemented in Java"))).check(matches(isDisplayed()))
    }

    @Test
    fun when_repofragment_list_click() {


        // arrange
        server.enqueue(MockResponse().setResponseCode(200).setBody(RepoJson.REPO_SUCCESS))
        activityTestRule.launchActivity(Intent())

        // assert
        onView(withText("Github")).check(matches(isDisplayed()))

        // act
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // assert
        onView(withText("Pull Request")).check(matches(isDisplayed()))
    }

 /*   @Test
    fun when_pullfragment_list() {
        activityTestRule.launchActivity(Intent())

        onView(withId(R.id.recycleView)).perform(scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        server.enqueue(MockResponse().setResponseCode(200).setBody(MockJsonTest.REPO_SUCCESS))

        onView(withText("Pull Request")).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.pull_title), withText("Title Pull 10"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.pull_body), withText("body 10"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.owner_name), withText("Test 10"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.owner_image), hasSibling(withText("Test 10")))).check(matches(isDisplayed()))
    }*/
}