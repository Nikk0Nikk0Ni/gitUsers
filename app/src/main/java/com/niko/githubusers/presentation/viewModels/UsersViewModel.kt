package com.niko.githubusers.presentation.viewModels

import android.app.Application
import android.net.ConnectivityManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niko.githubusers.data.repository.ListUsersRepositoryImpl
import com.niko.githubusers.domain.models.User
import com.niko.githubusers.domain.useCases.GetUsersList
import com.niko.githubusers.domain.useCases.UpdateUserList
import com.niko.githubusers.domain.useCases.UploadUserList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel(private val application: Application) : ViewModel() {
    private val repository = ListUsersRepositoryImpl
    private val getUserList = GetUsersList(repository)
    private val uploadUserList = UploadUserList(repository)
    private val updateUserList = UpdateUserList(repository)

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _isNetworkAvailable = MutableLiveData<Boolean>(false)
    val isNetworkAvailable: LiveData<Boolean>
        get() = _isNetworkAvailable


    private fun checkConnection(): Boolean {
        val connectivityManager =
            application.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        _isNetworkAvailable.value = networkInfo != null && networkInfo.isConnected
        return isNetworkAvailable.value == true
    }

    fun getUsersList(): LiveData<List<User>> {
        return getUserList()
    }

    fun uploadUserList() {
        if (checkConnection()) {
            viewModelScope.launch {
                uploadUserList.invoke()
                _isLoading.postValue(true)
            }
        }
    }

    fun updateUserList() {
        if (checkConnection()) {
            viewModelScope.launch {
                updateUserList.invoke()
                _isLoading.postValue(true)
            }
        }
    }

    fun resetIsLoading() {
        _isLoading.value = false
    }

}