package com.zatec.gotapp

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class HousesListFragmentTest {


    @Test
    fun givenAppWhenHousesTabClickedThenHousesFragmentIsVisibleWithCorrectTitle(){
        val scenario = launchFragmentInContainer<LandingFragment>()

        onView(withId(R.id.housesListFragment)).perform(click())
        onView(withId(R.id.houses_root))
            .check(matches(isDisplayed()))
        onView(withId(R.id.toolbar))
            .check(matches(withText("Houses")))
    }
}