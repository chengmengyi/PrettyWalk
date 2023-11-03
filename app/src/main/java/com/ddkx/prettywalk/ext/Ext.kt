package com.ddkx.prettywalk.ext

import android.content.Context
import android.widget.Toast
import com.ddkx.prettywalk.prettyApp
import java.text.SimpleDateFormat
import java.util.*

fun dp2px(dpValue: Float): Int {
    val scale: Float = prettyApp.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun showToast(string: String){
    Toast.makeText(prettyApp, string, Toast.LENGTH_SHORT).show()
}

fun twoPoint(d:Double)="%.2f".format(d)

fun formatTime(time:Long)= SimpleDateFormat("MMM d,yyyy", Locale.ENGLISH).format(Date(time))