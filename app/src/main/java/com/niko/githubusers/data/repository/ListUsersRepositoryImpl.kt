package com.niko.githubusers.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.niko.githubusers.data.api.users_api
import com.niko.githubusers.domain.models.User
import com.niko.githubusers.domain.models.UserDetail
import com.niko.githubusers.domain.repository.ListUsersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object ListUsersRepositoryImpl : ListUsersRepository {
    private var from = 0
    const val AMOUNT = 60
    private val listUsersLD = MutableLiveData<List<User>>()
    private val userDetailLD = MutableLiveData<UserDetail>()
    private var usersList = mutableSetOf<User>()


    override fun getUsersList(): LiveData<List<User>> {
        return listUsersLD
    }

    override suspend fun setUserDetail(userName: String) {
        if (users_api.getUserDetail(userName).isSuccessful &&
            users_api.getUserDetail(userName).body() != null
        ) {
            userDetailLD.postValue(users_api.getUserDetail(userName).body())
        }
    }

    override fun getUserDetail(): LiveData<UserDetail> {
        return userDetailLD
    }

    override suspend fun updateList() {
        usersList = mutableSetOf()
        from = 0
        uploadList()
    }

    override suspend fun uploadList() {
        if (users_api.getUsersList(AMOUNT, from).isSuccessful) {
            usersList.addAll(users_api.getUsersList(AMOUNT, from).body() ?: emptyList())
            listUsersLD.postValue(usersList.toList())
            from = usersList.last().id
            Log.e("AUF","${users_api.getUsersList(AMOUNT, from).body()?.last()}")
        }
    }
}
