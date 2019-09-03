package com.github.aminullah.chatup.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.aminullah.chatup.R
import com.github.aminullah.chatup.model.UsersModel
import de.hdodenhof.circleimageview.CircleImageView
import java.util.ArrayList

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    var context: Context
    var list = arrayListOf<UsersModel>()

    constructor(context: Context): super() {
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_users, parent, false)
        return ViewHolder(itemView = itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(list[position].avatar).into(holder.imgContactAvatar)
        holder.tvUsername.text = list[position].name
        holder.tvStatus.text = list[position].status
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItems(newlist: ArrayList<UsersModel>) {
        list.clear()
        list.addAll(newlist)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgContactAvatar = itemView.findViewById<CircleImageView>(R.id.imgContactAvatar)
        val tvUsername = itemView.findViewById<AppCompatTextView>(R.id.tvUsername)
        val tvStatus = itemView.findViewById<AppCompatTextView>(R.id.tvStatus)
    }
}