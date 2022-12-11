package com.zatec.gotapp


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import junit.framework.AssertionFailedError
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeoutException

@LargeTest
@RunWith(AndroidJUnit4::class)
class TestFirstItemOnLaunch {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testFirstItemOnLaunch() {

        val recyclerView = onView(
            withId(R.id.recycler_view)
        )


        //todo replace with some implementation of IdlingResource, this assumes network/db calls calls will take a max and min of 5 secs
        Thread.sleep(5000)

        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val textView = onView(
            allOf(
                withId(com.zatec.features.books.R.id.display_name), withText("A Game of Thrones"),
                withParent(
                    allOf(
                        withId(R.id.root),
                        withParent(withId(R.id.recycler_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("A Game of Thrones")))

        val textView2 = onView(
            allOf(
                withId(com.zatec.features.books.R.id.isbn), withText("978-0553103540"),
                withParent(
                    allOf(
                        withId(R.id.root),
                        withParent(withId(R.id.recycler_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("978-0553103540")))

//        val textView3 = onView(
//            allOf(
//                withId(com.zatec.features.books.R.id.authors), withText("George R. R. Martin"),
//                withParent(
//                    allOf(
//                        withId(R.id.root),
//                        withParent(withId(R.id.recycler_view))
//                    )
//                ),
//                isDisplayed()
//            )
//        )
//        textView3.check(matches(withText("George R. R. Martin")))

        val textView4 = onView(
            allOf(
                withId(com.zatec.features.books.R.id.pages), withText("694 pages"),
                withParent(
                    allOf(
                        withId(R.id.root),
                        withParent(withId(R.id.recycler_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("694 pages")))

        val textView5 = onView(
            allOf(
                withId(com.zatec.features.books.R.id.date), withText("Aug 01, 1996"),
                withParent(
                    allOf(
                        withId(R.id.root),
                        withParent(withId(R.id.recycler_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("Aug 01, 1996")))
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

fun ViewInteraction.waitUntilVisible(timeout: Long): ViewInteraction {
    val startTime = System.currentTimeMillis()
    val endTime = startTime + timeout

    do {
        try {
            check(matches(isDisplayed()))
            return this
        } catch (e: AssertionFailedError) {
            Thread.sleep(50)
        }
    } while (System.currentTimeMillis() < endTime)

    throw TimeoutException()
}
