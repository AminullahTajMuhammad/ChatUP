package com.github.aminullah.chatup.activities.users_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aminullah.chatup.R
import com.github.aminullah.chatup.activities.porfile_screen.ProfileActivity
import com.github.aminullah.chatup.adapters.UsersAdapter
import com.github.aminullah.chatup.model.UsersModel
import com.github.aminullah.chatup.utils.gotoActivity
import com.github.aminullah.chatup.utils.setActionBarText
import com.github.aminullah.chatup.utils.setActionBarColor
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity(), UsersContract.Views {

    lateinit var presenter: UsersPresenter
    lateinit var mAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        this.setActionBarText("ChatUP")
        this.setActionBarColor(R.color.colorApp)

        presenter = UsersPresenter(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_user_screen, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.menu_item_more -> {
                gotoActivity(this, ProfileActivity::class.java)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun setupViews() {
        mAdapter = UsersAdapter(this)
        UserRecycler.apply {
            layoutManager = LinearLayoutManager(this@UsersActivity)
            adapter = mAdapter
        }
    }

    override fun setupListeners() {

    }

    override fun addItems(list: ArrayList<UsersModel>) {
        mAdapter.setItems(list)
    }
}
