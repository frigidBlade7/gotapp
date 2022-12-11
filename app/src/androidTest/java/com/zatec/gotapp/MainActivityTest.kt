package com.zatec.gotapp

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Test
    fun givenTheAppWhenLaunchedThenViewIsDisplayed(){
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.root)).check(matches(isDisplayed()))
    }
}