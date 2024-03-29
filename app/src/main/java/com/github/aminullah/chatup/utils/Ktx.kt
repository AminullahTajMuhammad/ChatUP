package com.github.aminullah.chatup.utils

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.pro.models.User
import com.github.aminullah.chatup.R
import com.github.aminullah.chatup.model.UsersModel

fun AppCompatActivity.gotoActivity(context: Context, cls: Class<*>) {
    this.startActivity(Intent(context, cls))
}
fun AppCompatActivity.gotoActivityWithFinih(context: Context, cls: Class<*>) {
    this.startActivity(Intent(context, cls))
    this.finish()
}

// Visibility extensions
fun View.visible() {
    this.visibility = View.VISIBLE
}
fun View.invisible() {
    this.visibility = View.INVISIBLE
}
fun View.gone() {
    this.visibility = View.GONE
}
// Action Bar extensions
fun AppCompatActivity.setActionBarColor(resID: Int) {
    supportActionBar?.setBackgroundDrawable(resources.getDrawable(resID))
}

fun AppCompatActivity.setActionBarText(title: String) {
    supportActionBar?.title = title
}

fun AppCompatActivity.setBackButton() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
}

//add users in userModel
fun User.addData(): UsersModel {
    return UsersModel(this.name, this.avatar, this.status, this.uid)
}