package com.niko.githubusers.presentation.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UsersDetailViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserDetailViewModel::class.java))
            return UserDetailViewModel(application) as T
        else
            throw RuntimeException("UserDetailViewModel != $modelClass")
    }
}