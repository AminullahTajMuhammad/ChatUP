package com.github.aminullah.chatup.activities.users_screen

import android.util.Log
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.core.UsersRequest
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import com.github.aminullah.chatup.model.UsersModel
import com.github.aminullah.chatup.utils.addData


class UsersPresenter : UsersContract.Actions {

    var _views: UsersContract.Views? = null
    var list = arrayListOf<UsersModel>()
    var uidList = mutableListOf<User>()

    constructor(_views: UsersContract.Views?) {
        this._views = _views
        initScreen()
    }

    override fun initScreen() {
        _views?.setupViews()
        _views?.setupListeners()
        getUsersList()
    }

//    fun loadDummyData() {
//        list.add(UsersModel("Amin", R.drawable.pp_1, "online"))
//        list.add(UsersModel("Wajahat", R.drawable.pp_2, "offline"))
//        list.add(UsersModel("Adeel", R.drawable.pp_3, "online"))
//        list.add(UsersModel("Chat Up Bot", R.drawable.pp_1, "online"))
//        list.add(UsersModel("Taj Muhammad", R.drawable.pp_3, "offline"))
//        _views?.addItems(list)
//    }

    override fun getUsersList() {
        val userRequest = UsersRequest.UsersRequestBuilder().setLimit(10).build()
        userRequest.fetchNext(object : CometChat.CallbackListener<List<User>>(){
            override fun onSuccess(usersList: List<User>?) {
                // logged in user
                val loggedInUser = CometChat.getLoggedInUser()
                if(usersList != null) {
                    // Check all users
                    for (user in usersList) {
                        // checker for logged in user (don't get logged in user from list)
                        if(loggedInUser.uid != user.uid) {
                            uidList.add(user)
                            list.add(user.addData())
                            CometChat.addUserListener(user.uid, object : CometChat.UserListener() {
                                override fun onUserOnline(onlineUser: User?) {
                                    Log.d("TAG", onlineUser?.name + "is online.")
                                    user.let {
                                        getUserById(list, user.uid)?.let {
                                            list[it].status = "online"
                                            _views?.notifyItemChanged(it)
                                        }
                                    }
                                }

                                override fun onUserOffline(offLineUser: User?) {
                                    Log.d("TAG", offLineUser?.name + "is offline.")
                                    user.let {
                                        getUserById(list, user.uid)?.let {
                                            list[it].status = "offline"
                                            _views?.notifyItemChanged(it)
                                        }
                                    }
                                }
                            })
                        }
                    }
                    _views?.addItems(list)
                } else {
                    _views?.generateErrorToast()
                }

                _views?.notifyDataSetChanged()
            }

            override fun onError(e: CometChatException?) {

            }

        })
    }

    private fun getUserById(userList: List<UsersModel>, uid: String): Int? {
        for ((i, user) in userList.withIndex()) {
            if (user.id == uid) return i
        }
        return null
    }

    override fun getAllUserIds(): List<User> = uidList
}