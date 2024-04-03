package com.niko.githubusers.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.niko.githubusers.R
import com.niko.githubusers.domain.models.User
import com.niko.githubusers.presentation.holders.UserListHolder

class UserListAdapter : ListAdapter<User, UserListHolder>(UserListComporator()){

    var tap : ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return UserListHolder(view)
    }

    override fun onBindViewHolder(holder: UserListHolder, position: Int) {
        holder.bind(getItem(position),tap)
    }
}