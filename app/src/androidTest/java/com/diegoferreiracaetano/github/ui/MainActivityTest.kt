package com.diegoferreiracaetano.github.ui


import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.diegoferreiracaetano.data.di.restMockModule
import com.diegoferreiracaetano.github.R
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.test.KoinTest


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest : KoinTest {


    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        loadKoinModules(restMockModule)
    }


    @Test
    fun when_repofragment_list() {
        activityTestRule.launchActivity(Intent())

        onView(withText("Github")).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.owner_name), withText("Diego"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.owner_image), hasSibling(withText("Diego")))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.pull_title), withText("Repository 1"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.pull_body), withText("description 1"))).check(matches(isDisplayed()))
    }

    @Test
    fun when_repofragment_list_click() {
        activityTestRule.launchActivity(Intent())
        onView(withText("Github")).check(matches(isDisplayed()))
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withText("Pull Request")).check(matches(isDisplayed()))
    }

    @Test
    fun when_pullfragment_list() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.recycleView)).perform(scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(R.id.recycleView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withText("Pull Request")).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.pull_title), withText("Title Pull 1"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.pull_body), withText("body 1"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.owner_name), withText("Diego"))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.owner_image), hasSibling(withText("Diego")))).check(matches(isDisplayed()))
    }

}