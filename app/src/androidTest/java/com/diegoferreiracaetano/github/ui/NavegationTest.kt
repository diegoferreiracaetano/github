package com.diegoferreiracaetano.github.ui

import android.content.Intent
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.diegoferreiracaetano.data.di.testModule
import com.diegoferreiracaetano.github.R
import com.diegoferreiracaetano.github.ui.pull.PullFragment
import com.diegoferreiracaetano.github.ui.pull.PullFragmentArgs
import com.diegoferreiracaetano.github.ui.repo.RepoFragmentDirections
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext

@LargeTest
@RunWith(AndroidJUnit4::class)
class NavegationTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    lateinit var server: MockWebServer

    @Before
    @Throws(Exception::class)
    fun setUp() {
        server = MockWebServer()
        StandAloneContext.loadKoinModules(testModule(server.url("/").toString()))
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun test() {

        server.setDispatcher(MockServerDispatcher().RequestDispatcher())

        val reponame = "iluwatar"
        val owerName = "java-design-patterns"
        val action = RepoFragmentDirections.actionNext(reponame, owerName)
        val scenario: FragmentScenario<PullFragment> = launchFragmentInContainer(action.arguments)

        scenario.onFragment { fragment ->
            fragment.arguments?.let {
                val args = PullFragmentArgs.fromBundle(it)
                assertThat(args.reponame, `is`(reponame))
                assertThat(args.ownername, `is`(owerName))
            }
        }

        activityTestRule.launchActivity(Intent())

        onView(ViewMatchers.withId(R.id.recycleView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        onView(ViewMatchers.withText("Pull Request")).check(matches(ViewMatchers.isDisplayed()))
    }
}