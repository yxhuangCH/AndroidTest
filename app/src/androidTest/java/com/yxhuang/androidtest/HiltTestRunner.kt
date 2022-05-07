package com.yxhuang.androidtest

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * Created by yxhuang
 * Date: 2022/5/7
 * Description:
 */
class HiltTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
            cl: ClassLoader?,
            className: String?,
            context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}