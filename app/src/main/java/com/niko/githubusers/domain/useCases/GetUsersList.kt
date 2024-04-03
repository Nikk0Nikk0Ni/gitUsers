package com.niko.githubusers.domain.useCases

import androidx.lifecycle.LiveData
import com.niko.githubusers.domain.models.User
import com.niko.githubusers.domain.repository.ListUsersRepository

class GetUsersList(private val repository : ListUsersRepository) {
    operator fun invoke() : LiveData<List<User>>{
        return repository.getUsersList()
    }
}