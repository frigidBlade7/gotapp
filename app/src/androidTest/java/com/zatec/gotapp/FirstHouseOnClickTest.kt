package com.zatec.gotapp


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FirstHouseOnClickTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun firstHouseOnClickTest() {
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.housesListFragment), withContentDescription("Houses"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_nav_view),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())


        //todo replace with some implementation of IdlingResource, this assumes network/db calls calls will take a max and min of 5 secs
        Thread.sleep(5000)

        val constraintLayout = onView(
            allOf(
                withId(R.id.root),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recycler_view),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        constraintLayout.perform(click())


        //todo replace with some implementation of IdlingResource, this assumes network/db calls calls will take a max and min of 5 secs
        Thread.sleep(5000)

        val textView = onView(
            allOf(
                withId(com.zatec.features.houses.R.id.title), withText("House Algood"),
                isDisplayed()
            )
        )
        textView.check(matches(withText("House Algood")))

        val textView2 = onView(
            allOf(
                withId(com.zatec.features.houses.R.id.lord), withText("A house has no Lord"),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("A house has no Lord")))

        val textView3 = onView(
            allOf(
                withId(com.zatec.features.houses.R.id.location), withText("The Westerlands"),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("The Westerlands")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
