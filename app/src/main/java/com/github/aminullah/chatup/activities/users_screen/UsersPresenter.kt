package com.github.aminullah.chatup.activities.users_screen

class UsersPresenter : UsersContract.Actions {

    var _views: UsersContract.Views? = null

    constructor(_views: UsersContract.Views?) {
        this._views = _views
        initScreen()
    }

    override fun initScreen() {
        _views?.setupViews()
        _views?.setupListeners()
        loadDummyData()
    }

    fun loadDummyData() {

    }
}