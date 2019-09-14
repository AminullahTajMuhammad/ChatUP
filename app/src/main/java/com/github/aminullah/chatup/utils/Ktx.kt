package com.github.aminullah.chatup.utils

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.pro.core.CometChat
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

fun Any.getUniqueListnerID(uid: String): String {
    return getCombinedID(CometChat.getLoggedInUser().uid, uid)
}

fun Any.getCombinedID(loginId: String, toId: String) : String
{
    var newid = ""

    var list = ArrayList<String>()
    list.add(loginId)
    list.add(toId)
    list.sort()

    newid = list[0] + "-" + list[1]

    return newid
}

fun Any.findUserById(usersList: List<UsersModel>, uid: String): Int? {
    var i=0
    usersList.forEach {

        if(it.id == uid) {
            return i
        }

        i++
    }
    return null
}