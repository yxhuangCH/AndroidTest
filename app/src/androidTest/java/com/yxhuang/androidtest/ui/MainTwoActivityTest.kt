package com.yxhuang.androidtest.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.yxhuang.androidtest.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by yxhuang
 * Date: 2022/8/1
 * Description:
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainTwoActivityTest {


    @get:Rule
    val activityRule = ActivityScenarioRule(MainTwoActivity::class.java)


    @Test
    fun ensureTextChangesWork(){
        // EditText 输入文本 "HELLO", 然后关闭软键盘
        onView(withId(R.id.inputField))
            .perform(typeText("HELLO"), closeSoftKeyboard())
        // 点击 changeText 按钮
        onView(withId(R.id.changeText))
            .perform(click())
        // 验证
        onView(withId(R.id.inputField)).check(matches(withText("Lalala")))
    }

    @Test
     fun changeTextNewActivity(){
         onView(withId(R.id.inputField))
             .perform(typeText("NewText"), closeSoftKeyboard())

        // 点击按钮
         onView(withId(R.id.switchActivity)).perform(click())

        // 验证已经是第二个 Activity
        onView(withId(R.id.resultView))
            .check(matches(withText("NewText")))

     }
}