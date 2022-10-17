package com.example.fooddeliveryapp

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.fooddeliveryapp.view.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class GoHomeTest {

    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, true, true)

    @Before
    fun setUp() {
        activityRule.launchActivity(Intent())
        Intents.init()
    }

    @Test
    fun sampleTest() {
        Espresso.onView(ViewMatchers.withResourceName("txtLogoTitle"))
            .check(ViewAssertions.matches(ViewMatchers.withText(MESSAGE)))
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    companion object {
        const val MESSAGE = "OrderAnyWay"
    }
}