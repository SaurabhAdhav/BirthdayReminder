package com.application


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_startup.*

class StartupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        txt_add_reminder.setOnClickListener {
           /* ll_option.visibility = View.GONE
            frame_layout.visibility = View.VISIBLE

            var fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            var fragment = AddReminderFragment()
            fragmentManager.beginTransaction().add(fragment, "add_reminder").commit()
            fragmentTransaction.add(R.id.frame_layout, fragment)*/
            intent = Intent(this@StartupActivity, AddReminderActivity::class.java)
            startActivity(intent)

        }
        txt_view_reminder.setOnClickListener {
            intent = Intent(this@StartupActivity, CardActivity::class.java)
            startActivity(intent)
        }
        txt_send_msg.setOnClickListener {
            intent = Intent(this@StartupActivity, SendMessage::class.java)
            startActivity(intent)
        }
    }
}
