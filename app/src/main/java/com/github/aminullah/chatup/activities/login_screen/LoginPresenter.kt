package com.github.aminullah.chatup.activities.login_screen

import android.util.Log
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User

class LoginPresenter : LoginContract.Actions {

    var _views: LoginContract.Views? = null

    constructor(_views: LoginContract.Views?) {
        this._views = _views
        initScreen()
    }


    override fun initScreen() {
        _views?.setupViews()
        _views?.setupListeners()
    }

    override fun login(uid: String) {
        CometChat.login(uid, _views?.getApiKey().toString(), object : CometChat.CallbackListener<User>() {
            override fun onSuccess(p0: User?) {
                Log.d("SUCCESS", "Login Success with ${p0?.name}, ${p0?.uid}")
                _views?.gotoUsersActivity()
            }

            override fun onError(p0: CometChatException?) {
                Log.d("ERROR", p0?.message)
            }

        })
    }
}