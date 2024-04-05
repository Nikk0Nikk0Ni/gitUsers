package com.niko.githubusers.presentation.viewModels

import android.app.Application
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
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

    private var _isNetworkAvailable = MutableLiveData<Boolean>(false)
    val isNetworkAvailable: LiveData<Boolean>
        get() = _isNetworkAvailable

    fun getDetail() : LiveData<UserDetail>{
        return getDetailViewModel()
    }

    fun setDetail(name: String) {
        if(checkConnection()) {
            viewModelScope.launch {
                setDetail.invoke(name)
                _isEnd.value = true
            }
        }
    }

    private fun checkConnection(): Boolean {
        val connectivityManager =
            application.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        _isNetworkAvailable.value = networkInfo != null && networkInfo.isConnected
        return isNetworkAvailable.value == true
    }

}