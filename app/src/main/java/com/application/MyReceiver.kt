package com.application


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

import java.util.Calendar

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val c1 = Calendar.getInstance()
        val d = c1.get(Calendar.DAY_OF_MONTH)
        val m = c1.get(Calendar.MONTH)
        var d1: Int
        var m1: Int
        val database: SQLiteDatabase
        val bdaydb = MyHelper(context, "bdaydb", null, 1)


        database = bdaydb.writableDatabase

        val sql = "select * from user_data"
        val c = database.rawQuery(sql, null)

        while (c.moveToNext()) {
            d1 = c.getInt(c.getColumnIndex("bday"))
            m1 = c.getInt(c.getColumnIndex("bmonth"))
            if (d1 == d && m1 == m) {

                //     SmsManager sms = SmsManager.getDefault();
                //      sms.sendTextMessage(c.getString(c.getColumnIndex("num")), null, "Happy Birthday!!!", null, null);

            }
        }
    }
}