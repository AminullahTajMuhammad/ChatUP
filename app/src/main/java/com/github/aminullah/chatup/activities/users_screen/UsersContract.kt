package com.github.aminullah.chatup.activities.users_screen

import com.cometchat.pro.models.User
import com.github.aminullah.chatup.model.UsersModel

interface UsersContract {
    interface Views {
        fun setupViews()
        fun setupListeners()
        fun addItems(list: ArrayList<UsersModel>)
        fun generateErrorToast()
        fun notifyItemChanged(position: Int)
        fun notifyDataSetChanged()
    }

    interface Actions {
        fun initScreen()
        fun getUsersList()
        fun getAllUserIds(): List<User>
    }
}