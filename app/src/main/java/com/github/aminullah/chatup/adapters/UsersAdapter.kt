package com.github.aminullah.chatup.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.aminullah.chatup.R
import com.github.aminullah.chatup.model.UsersModel
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

    }
}