package com.zatec.gotapp

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class BooksListFragmentTest {

    @Test
    fun givenAppWhenLaunchedThenBookFragmentIsVisibleWithCorrectTitle(){
        val scenario = launchFragmentInContainer<BooksListFragment>()

        onView(withId(R.id.book_root)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(withText("Books")))
    }


    @Test
    fun givenAppWhenBooksTabClickedThenBooksFragmentIsVisibleWithCorrectTitle(){
        val scenario = launchFragmentInContainer<LandingFragment>()

        onView(withId(R.id.booksListFragment)).perform(ViewActions.click())

        onView(withId(R.id.book_root))
            .check(matches(isDisplayed()))
        onView(withId(R.id.toolbar))
            .check(matches(withText("Books")))
    }
}