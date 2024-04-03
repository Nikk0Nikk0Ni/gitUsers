package com.niko.githubusers.presentation.viewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niko.githubusers.data.repository.ListUsersRepositoryImpl
import com.niko.githubusers.domain.models.UserDetail
import com.niko.githubusers.domain.useCases.GetUserDetail
import com.niko.githubusers.domain.useCases.SetUserDetail
import kotlinx.coroutines.launch

class UserDetailViewModel(private val application: Application) : ViewModel() {
    private val repository = ListUsersRepositoryImpl
    private val getDetailViewModel = GetUserDetail(repository)
    private val setDetail = SetUserDetail(repository)

    private val _isEnd = MutableLiveData(false)
    val isEnd : LiveData<Boolean>
        get() = _isEnd

    fun getDetail() : LiveData<UserDetail>{
        return getDetailViewModel()
    }

    fun setDetail(name: String) {
        viewModelScope.launch {
            setDetail.invoke(name)
            _isEnd.value = true
        }
    }

}