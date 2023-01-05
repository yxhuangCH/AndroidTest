package com.yxhuang.androidtest.ui.intent

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.yxhuang.androidtest.R


/**
 * Created by yxhuang
 * Date: 2023/1/5
 * Description:
 */
class DialerActivity : Activity() {
    private var mCallerNumber: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialer)
        mCallerNumber = findViewById<View>(R.id.edit_text_caller_number) as EditText
    }

    fun onCall(view: View?) {
        val hasCallPhonePermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
        if (hasCallPhonePermission){
            startActivity(createCallIntentFromNumber())
        } else {
            Toast.makeText(this,
                           R.string.warning_call_phone_permission,
                           Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun onPickContact(view: View?) {
        val pickContactIntent = Intent(this, ContactsActivity::class.java)
        startActivityForResult(pickContactIntent, REQUEST_CODE_PICK)
    }

    private fun createCallIntentFromNumber(): Intent {
        val intentToCall = Intent(Intent.ACTION_CALL)
        val number = mCallerNumber!!.text.toString()
        intentToCall.data = Uri.parse("tel:$number")
        return intentToCall
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_CODE_PICK) {
            if (resultCode == RESULT_OK) {
                mCallerNumber!!.setText(data.extras?.getString(ContactsActivity.KEY_PHONE_NUMBER) ?: "")
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_PICK = 16
    }
}