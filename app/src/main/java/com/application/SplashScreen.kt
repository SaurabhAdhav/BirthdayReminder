package com.application


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity



class SplashScreen : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

       /* window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
*/

        Handler().postDelayed({
            val it = Intent(this@SplashScreen, StartupActivity::class.java)
            startActivity(it)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object {

        private val SPLASH_TIME_OUT = 3000
    }

}
