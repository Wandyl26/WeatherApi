package com.wandyldiaz.weather

import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class climateTest {

    @Test
    @Throws(InterruptedException::class)
    fun testSearch() {
        clickInput(R.id.search)
        keyPressInput("BUCARAMANGA", R.id.search)
        Thread.sleep(10000)
        Espresso.onView(Matchers.allOf(ViewMatchers.withText("Bucaramanga")))
            .check(ViewAssertions.matches(ViewMatchers.withText("Bucaramanga")))
    }

    @Test
    @Throws(InterruptedException::class)
    fun testForecast() {
        clickInput(R.id.search)
        keyPressInput("BUCARAMANGA", R.id.search)
        Thread.sleep(10000)
        clickInput(R.id.item)
        Thread.sleep(10000)
        Espresso.onView(Matchers.allOf(ViewMatchers.withText("Bucaramanga")))
            .check(ViewAssertions.matches(ViewMatchers.withText("Bucaramanga")))
    }

    private fun keyPressInput(valueToWrite: String, editText: Int) {
        Espresso.onView(ViewMatchers.withId(editText))
            .perform(ViewActions.replaceText(valueToWrite), ViewActions.closeSoftKeyboard())
    }

    private fun clickInput(view: Int) {
        Espresso.onView(ViewMatchers.withId(view)).perform(ViewActions.click())
    }

    private fun pressBack() {
        val mDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        mDevice.pressBack()
    }

    companion object {
        private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int
        ): Matcher<View> {
            return object : TypeSafeMatcher<View>() {
                override fun describeTo(description: Description) {
                    description.appendText("Child at position $position in parent ")
                    parentMatcher.describeTo(description)
                }

                public override fun matchesSafely(view: View): Boolean {
                    val parent: ViewParent = view.parent
                    return (parent is ViewGroup && parentMatcher.matches(parent)
                            && view == (parent as ViewGroup).getChildAt(position))
                }
            }
        }
    }
}