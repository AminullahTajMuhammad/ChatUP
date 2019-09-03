package com.github.aminullah.chatup.activities.login_screen

interface LoginContract {
    interface Views {
        fun setupViews()
        fun setupListeners()
        fun gotoUsersActivity()
        fun getApiKey(): String
    }

    interface Actions {
        fun initScreen()
        fun login(uid: String)
    }
}