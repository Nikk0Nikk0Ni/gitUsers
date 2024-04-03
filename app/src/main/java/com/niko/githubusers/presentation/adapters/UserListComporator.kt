package com.niko.githubusers.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.niko.githubusers.domain.models.User

class UserListComporator : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}