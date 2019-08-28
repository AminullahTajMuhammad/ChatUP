package com.github.aminullah.chatup.utils

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.gotoActivity(context: Context, cls: Class<*>) {
    this.startActivity(Intent(context, cls))
}
fun AppCompatActivity.gotoActivityWithFinih(context: Context, cls: Class<*>) {
    this.startActivity(Intent(context, cls))
    this.finish()
}