package com.github.aminullah.chatup.activities.users_screen

import com.github.aminullah.chatup.model.UsersModel

interface UsersContract {
    interface Views {
        fun setupViews()
        fun setupListeners()
        fun addItems(list: ArrayList<UsersModel>)
    }

    interface Actions {
        fun initScreen()
    }
}