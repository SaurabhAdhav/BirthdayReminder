package com.application


import android.app.Activity
import android.graphics.Typeface
import android.graphics.fonts.Font
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import kotlin.coroutines.coroutineContext


object Utils {
    fun setTypeFaceFontAwesome(activity: Activity, textview: TextView) {
        val tf2 = Typeface.createFromAsset(activity.assets, "fontawesome-webfont.ttf")
        textview.typeface = tf2



    }



}
