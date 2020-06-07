package com.application

import android.app.DatePickerDialog
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.actionbar_header.*
import kotlinx.android.synthetic.main.activity_add_reminder.*
import java.util.*


class AddReminderActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    var date: Int = 0
    var month: Int = 0
    var year: Int = 0
    lateinit var database: SQLiteDatabase
    var string: String = ""
    var n: String = ""
    var num: String = ""
    val mobilePattern = "[0-9]{10}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reminder)

        Utils.setTypeFaceFontAwesome(this, txtNameIcon)
        Utils.setTypeFaceFontAwesome(this, txtDobIcon)
        Utils.setTypeFaceFontAwesome(this, txtNumberIcon)

        txtDobIcon.setOnClickListener {
            val calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            date = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(this, this, year, month, date)
            datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
            datePickerDialog.show()
        }
        btnViewReminders.setOnClickListener {
            var intent = Intent(this, ViewEntry::class.java)
            startActivity(intent)
        }
        btnSave.setOnClickListener {
            n = edtName.text.toString()
            if (n.length == 0) {
                edtName.error = "Enter name..!!"
            }
            num = edtxtNumber.text.toString()
            if (!num.matches(mobilePattern.toRegex())) {
                edtxtNumber.error = "Enter valid phone number..!!"
            }
            if (edtName.length() != 0 && edtxtNumber.length() != 0 && txtDob.length() != 0) {
                string = "insert into user_data values('$n','$num',$month,$date,$year)"
                database.execSQL(string)
                Toast.makeText(this, "Record Inserted!! ", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        btn_back.setOnClickListener { finish() }

        val ob = MyHelper(this, "bdaydb", null, 1)
        database = ob.writableDatabase
    }


    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        var monthOfYear = monthOfYear
        date = dayOfMonth
        month = monthOfYear++
        txtDob.setText("$dayOfMonth/$monthOfYear/$year")
    }
}