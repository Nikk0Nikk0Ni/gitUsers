package com.niko.githubusers.presentation.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.niko.githubusers.R
import com.niko.githubusers.databinding.UserItemBinding
import com.niko.githubusers.domain.models.User
import com.squareup.picasso.Picasso

class UserListHolder(val view : View) : RecyclerView.ViewHolder(view) {
    val binding = UserItemBinding.bind(view)
    fun bind(user : User, tap : ((User) -> Unit)? = null){
        Picasso.get().load(user.avatar_url).into(binding.userIcon)
        binding.tvUserId.text =  String.format(view.context.getString(R.string.id_d),user.id.toString())
        binding.tvUserLogin.text = String.format(view.context.getString(R.string.name_s),user.login)
        itemView.setOnClickListener{
            tap?.let {
                it(user)
            }
        }
    }
}