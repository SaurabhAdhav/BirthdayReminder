package com.application

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.actionbar_header.*
import kotlinx.android.synthetic.main.activity_card.*
import java.util.ArrayList

class CardActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_card)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager?


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
        val users = ArrayList<User>()


        /*for (item:Int in nameArrayList.size..0) {
            users.add(User(nameArrayList.get(item),phoneArrayList.get(item),dateArrayList.get(item),monthArrayList.get(item),yearArrayList.get(item)))

        }
*/

        for (item in 0 until nameArrayList.size) {
            users.add(User(nameArrayList.get(item),phoneArrayList.get(item),dateArrayList.get(item),monthArrayList.get(item),yearArrayList.get(item)))
        }


        val adapter = CardEntryAdapter(users)
        recycler_view.adapter =adapter

    }
}
