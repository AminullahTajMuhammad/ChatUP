package com.github.aminullah.chatup.application

import android.app.Application
import android.util.Log
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import com.github.aminullah.chatup.R

class ChatUpApp: Application() {
    override fun onCreate() {
        super.onCreate()
        CometChat.init(this, getString(R.string.app_id), object : CometChat.CallbackListener<String>() {

            override fun onSuccess(p0: String?) {
                Log.d("SUCCESS", "Initialization Successful")
            }

            override fun onError(p0: CometChatException?) {
                Log.d("SUCCESS", "Initialization Failed ${p0?.stackTrace}")
            }

        })
    }
}