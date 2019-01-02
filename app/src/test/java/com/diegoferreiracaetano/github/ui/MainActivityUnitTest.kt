package com.diegoferreiracaetano.github.ui

import androidx.navigation.findNavController
import com.diegoferreiracaetano.github.R
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityUnitTest {


    @Test
    fun GivenMainActivity_WhenInitializeUi_ShouldNavigation() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val navagation = activity.findNavController(R.id.nav_host_fragment).navigateUp()
        assertThat(activity.onSupportNavigateUp(), `is`(navagation))
    }
}