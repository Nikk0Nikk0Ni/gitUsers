package com.niko.githubusers.domain.repository

import androidx.lifecycle.LiveData
import com.niko.githubusers.domain.models.User
import com.niko.githubusers.domain.models.UserDetail

interface ListUsersRepository {

    fun getUsersList(): LiveData<List<User>>
    suspend fun setUserDetail(userName : String)
    fun getUserDetail(): LiveData<UserDetail>
    suspend fun updateList()
    suspend fun uploadList()

}