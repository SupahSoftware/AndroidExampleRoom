package com.example.androidexampleroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private var userList = listOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false)
        )
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    fun setUsers(users: List<User>) {
        userList = users
        notifyDataSetChanged()
    }
}

class UserViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(user: User) {
        view.list_item_username.text = user.username
        view.list_item_age.text = user.age.toString()
        view.list_item_is_cool.text = user.isCool.toString()
        view.list_item_id.text = user.id
    }
}