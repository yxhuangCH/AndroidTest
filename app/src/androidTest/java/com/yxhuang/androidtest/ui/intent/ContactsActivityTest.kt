package com.yxhuang.androidtest.ui.intent

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.yxhuang.androidtest.R
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.assertion.ViewAssertions.matches

/**
 * Created by yxhuang
 * Date: 2023/1/4
 * Description:
 */
class ContactsActivityTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule(ContactsActivity::class.java)

    @Test
    fun activityResult_DisplaysContactsPhoneNumber() {
        // Given
        val resultData = Intent()
        val phoneNumber = "123-456-789"
        resultData.putExtra("phone", phoneNumber)
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)

        //when
        intending(toPackage("com.android.contacts")).respondWith(result)

        onView(withId(R.id.button_call_number)).perform(click())

        // Vilify
        onView(withId(R.id.edit_text_caller_number)).check(matches(withText(phoneNumber)))

    }
}