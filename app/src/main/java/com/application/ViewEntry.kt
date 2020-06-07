package com.application


import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.actionbar_header.*
import kotlinx.android.synthetic.main.activity_view_entry.*

import java.util.ArrayList

class ViewEntry : AppCompatActivity() {

    lateinit var db: SQLiteDatabase
    lateinit var cursor: Cursor
    lateinit var myHelper: MyHelper
    var nameArrayList = ArrayList<String>()
    var phoneArrayList = ArrayList<String>()
    var monthArrayList = ArrayList<String>()
    var dateArrayList = ArrayList<String>()
    var yearArrayList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_entry)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_back.setOnClickListener { finish() }
        toolbar_title.text = getString(R.string.view_reminders)

        myHelper = MyHelper(this, "bdaydb", null, 1)
        db = myHelper.readableDatabase
        cursor = db.rawQuery("select * from user_data", null)

        val index = cursor.getColumnIndex("bday")
        if (cursor.moveToFirst()) {
            do {


                nameArrayList.add(cursor.getString(cursor.getColumnIndex("name")))
                phoneArrayList.add(cursor.getString(cursor.getColumnIndex("num")))
                dateArrayList.add(cursor.getString(cursor.getColumnIndex("bmonth")))
                val mnt = cursor.getString(index)
                val m = Integer.parseInt(mnt) + 1
                monthArrayList.add(m.toString())
                yearArrayList.add(cursor.getString(cursor.getColumnIndex("yr")))
            } while (cursor.moveToNext())
        }
        val ad1 = ArrayAdapter<String>(this, R.layout.name_list, nameArrayList)
        listViewName?.adapter = ad1
        val ad2 = ArrayAdapter<String>(this, R.layout.name_list, phoneArrayList)
        listViewContactNumber?.adapter = ad2
        val ad3 = ArrayAdapter<String>(this, R.layout.name_list, dateArrayList)
        listViewDate?.adapter = ad3
        val ad4 = ArrayAdapter<String>(this, R.layout.name_list, monthArrayList)
        listViewMonth?.adapter = ad4
        val ad5 = ArrayAdapter<String>(this, R.layout.name_list, yearArrayList)
        listViewYear?.adapter = ad5
        cursor.close()
    }
}

