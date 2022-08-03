package com.yxhuang.androidtest.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.yxhuang.androidtest.R


/**
 * Created by yxhuang
 * Date: 2022/8/1
 * Description:
 */
class SecondActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val viewById = findViewById<View>(R.id.resultView) as TextView
        val inputData = intent.extras
        val input = inputData!!.getString("input")
        viewById.text = input
    }

}