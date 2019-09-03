package com.github.aminullah.chatup.activities.users_screen

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.models.User
import com.github.aminullah.chatup.R
import com.github.aminullah.chatup.activities.porfile_screen.ProfileActivity
import com.github.aminullah.chatup.adapters.UsersAdapter
import com.github.aminullah.chatup.model.UsersModel
import com.github.aminullah.chatup.utils.setActionBarColor
import com.github.aminullah.chatup.utils.setActionBarText
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
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("user_data", currentUserData() as Parcelable)
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

    override fun onPause() {
        super.onPause()
        val userUids = presenter.getAllUserIds()
        for(user in userUids) {
            CometChat.removeCallListener(user.uid)
        }
    }

    override fun setupListeners() {

    }

    override fun addItems(list: ArrayList<UsersModel>) {
        mAdapter.setItems(list)
    }

    private fun currentUserData(): User {
        return CometChat.getLoggedInUser()
    }

    override fun generateErrorToast() = Toast.makeText(this, "Couldn't Load users", Toast.LENGTH_SHORT).show()
    override fun notifyItemChanged(position: Int) = mAdapter.notifyItemInserted(position)
    override fun notifyDataSetChanged() = mAdapter.notifyDataSetChanged()
}
