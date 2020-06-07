package com.application

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

import kotlinx.android.synthetic.main.actionbar_header.*
import kotlinx.android.synthetic.main.activity_send_msg.*


class SendMessage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_msg)

        btn_back.setOnClickListener { finish() }
        toolbar_title.text = getString(R.string.send_message)

       txt_phone_no.setOnClickListener { v -> pickContact(v) }
        btn_send_sms.setOnClickListener {
            //                if (ContextCompat.checkSelfPermission(SendMessage.this, Manifest.permission.SEND_SMS)
            //                        == PackageManager.PERMISSION_GRANTED) {
            //                    // Permission is granted
            ActivityCompat.requestPermissions(
                this@SendMessage,
                arrayOf(Manifest.permission.SEND_SMS),
                1
            )
            //                    if (edt_msg.length() != 0 && txt_phone_no.length() != 0)
            //                        sendSMS(getBaseContext(), txt_phone_no.getText().toString(), edt_msg.getText().toString());
            //                }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (edt_msg.length() != 0 && txt_phone_no.length() != 0)
                        sendSMS(baseContext, txt_phone_no.text.toString(), edt_msg.text.toString())
                } else {
                    Toast.makeText(
                        this@SendMessage,
                        "Permission denied to send sms",
                        Toast.LENGTH_SHORT
                    ).show()
                    ActivityCompat.requestPermissions(
                        this@SendMessage,
                        arrayOf(Manifest.permission.SEND_SMS),
                        1
                    )
                }
                return
            }
        }
    }

    fun sendSMS(context: Context, phoneNo: String, msg: String) {
        try {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.SEND_SMS
                ) === PackageManager.PERMISSION_DENIED
            ) {
                Toast.makeText(context, "Please allow permissions", Toast.LENGTH_LONG).show()
                return
            }
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNo, null, msg, null, null)
            Toast.makeText(context, "SMS sent", Toast.LENGTH_LONG).show()
            txt_phone_no.setText("")
            edt_msg.setText("")
        } catch (ex: Exception) {
            Toast.makeText(context, ex.message, Toast.LENGTH_LONG).show()
            ex.printStackTrace()
        }

    }

    fun pickContact(v: View) {
        val contactPickerIntent = Intent(
            Intent.ACTION_PICK,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // check whether the result is ok
        if (resultCode == Activity.RESULT_OK) {
            // Check for the request code, we might be usign multiple startActivityForReslut
            when (requestCode) {
                RESULT_PICK_CONTACT -> contactPicked(data!!)
            }
        } else {
            Log.e("Send SMS", "Failed to pick contact")
        }
    }


    private fun contactPicked(data: Intent) {
        var cursor: Cursor? = null
        try {
            var phoneNo: String? = null
            var name: String? = null
            // getData() method will have the Content Uri of the selected contact
            val uri = data.data
            //Query the content uri
            cursor = contentResolver.query(uri!!, null, null, null, null)
            cursor!!.moveToFirst()
            // column index of the phone number
            val phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            // column index of the contact name
            val nameIndex =
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            phoneNo = cursor.getString(phoneIndex)
            name = cursor.getString(nameIndex)
            // Set the value to the textviews
            txt_phone_no.setText(phoneNo)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    companion object {
        private val RESULT_PICK_CONTACT = 85500

    }
}
