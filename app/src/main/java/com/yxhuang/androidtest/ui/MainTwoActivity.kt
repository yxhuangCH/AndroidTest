package com.yxhuang.androidtest.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.yxhuang.androidtest.R


/**
 * Created by yxhuang
 * Date: 2022/8/1
 * Description:
 */
class MainTwoActivity : Activity() {

    var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_two_layout)
        editText = findViewById<View>(R.id.inputField) as EditText
    }


    fun onClick(view: View) {
        when (view.id) {
            R.id.changeText -> editText!!.setText("Lalala")
            R.id.switchActivity -> {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("input", editText!!.text.toString())
                startActivity(intent)
            }
        }
    }
}