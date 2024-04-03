package com.niko.githubusers.presentation.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UsersViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UsersViewModel::class.java))
            return UsersViewModel(application) as T
        else
            throw RuntimeException("Users View Model != $modelClass")
    }
}