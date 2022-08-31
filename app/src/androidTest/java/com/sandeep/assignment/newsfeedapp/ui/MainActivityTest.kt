package com.sandeep.assignment.newsfeedapp.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.sandeep.assignment.newsfeedapp.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_MainActivityShowStatus() {
        Espresso.onView(withId(R.id.main_activity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_MainActivityFragmentLoadStatus() {
        Espresso.onView(withId(R.id.nav_host_fragment_content_main))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}