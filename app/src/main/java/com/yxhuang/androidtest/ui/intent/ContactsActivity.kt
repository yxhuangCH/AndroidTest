package com.yxhuang.androidtest.ui.intent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import com.yxhuang.androidtest.R


/**
 * Created by yxhuang
 * Date: 2022/8/4
 * Description:
 */
class ContactsActivity : Activity() {

    companion object{
        const val KEY_PHONE_NUMBER = "key_phone_number"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        setResult(Activity.RESULT_OK, createResultData("896-745-231"))
    }

    @VisibleForTesting
    fun createResultData(phoneNumber: String?): Intent? {
        val resultData = Intent()
        resultData.putExtra(KEY_PHONE_NUMBER, phoneNumber)
        return resultData
    }
}