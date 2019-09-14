package com.github.aminullah.chatup.activities.users_screen

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.core.UsersRequest
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import com.github.aminullah.chatup.R
import com.github.aminullah.chatup.model.UsersModel
import com.github.aminullah.chatup.utils.addData
import com.github.aminullah.chatup.utils.findUserById
import com.github.aminullah.chatup.utils.getUniqueListnerID
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    var usersList = arrayListOf<UsersModel>()
    lateinit var mAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        setupViews()
        fetchUsers()
    }

    private fun setupViews() {
        mAdapter = UsersAdapter(this)
        UserRecycler.apply {
            layoutManager = LinearLayoutManager(this@UserActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
    }

    private fun fetchUsers() {
        val userRequest = UsersRequest.UsersRequestBuilder().setLimit(10).build()
        userRequest.fetchNext(object : CometChat.CallbackListener<List<User>>() {
            override fun onSuccess(userList: List<User>?) {
                if(userList != null) {
                    val loggedInUser = CometChat.getLoggedInUser()

                    for (user in userList) {
                        if(loggedInUser.uid != user.uid) {
                            usersList.add(user.addData())

                            CometChat.addUserListener(getUniqueListnerID(user.uid), object : CometChat.UserListener() {
                                override fun onUserOnline(user: User?) {
                                    super.onUserOnline(user)
                                    user?.let {
                                        findUserById(usersList, user.uid)?.let {
                                            usersList[it].status = "Online"
                                            mAdapter.notifyItemChanged(it)
                                        }
                                    }
                                }

                                override fun onUserOffline(user: User?) {
                                    super.onUserOffline(user)
                                    user?.let {
                                        findUserById(usersList, user.uid)?.let {
                                            usersList[it].status = "Offline"
                                            mAdapter.notifyItemChanged(it)
                                        }
                                    }
                                }
                            })
                        }
                    }
                    mAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@UserActivity, "Could not load User list is null", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onError(p0: CometChatException?) {
                Log.d("Error", p0?.details)
            }

        })

    }

    override fun onPause() {
        super.onPause()
        usersList.forEach {
            CometChat.removeUserListener(getUniqueListnerID(it.id))
        }
    }

    inner class UsersAdapter(private val context: Context): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_users, parent, false)
            return ViewHolder(itemView = itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Glide.with(context).load(usersList[position].avatar).into(holder.imgContactAvatar)
            holder.tvUsername.text = usersList[position].name
            holder.tvStatus.text = usersList[position].status

            if(holder.tvStatus.text == "Online") {
                holder.tvStatus.setTextColor(Color.GREEN)
            } else {
                holder.tvStatus.setTextColor(Color.DKGRAY)
            }
        }

        override fun getItemCount(): Int {
            return usersList.size
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val imgContactAvatar = itemView.findViewById<CircleImageView>(R.id.imgContactAvatar)
            val tvUsername = itemView.findViewById<AppCompatTextView>(R.id.tvUsername)
            val tvStatus = itemView.findViewById<AppCompatTextView>(R.id.tvStatus)
        }
    }
}
