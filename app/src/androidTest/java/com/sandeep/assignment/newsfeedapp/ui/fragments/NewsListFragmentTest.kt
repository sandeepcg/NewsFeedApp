package com.sandeep.assignment.newsfeedapp.ui.fragments

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.mapapplication.hiltUtil.launchFragmentInHiltContainer
import com.sandeep.assignment.newsfeedapp.R
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class NewsListFragmentTest {

    @Before
    fun init() {
        launchFragmentInHiltContainer<NewsListFragment>()
    }

    @Test
    fun test_phoneBaseFragmentDisplayStatus() {
        Espresso.onView(ViewMatchers.withId(R.id.nav_host_fragment_content_main))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}