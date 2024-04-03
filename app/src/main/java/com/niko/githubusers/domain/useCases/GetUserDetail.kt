package com.niko.githubusers.domain.useCases

import androidx.lifecycle.LiveData
import com.niko.githubusers.domain.models.UserDetail
import com.niko.githubusers.domain.repository.ListUsersRepository

class GetUserDetail(private val repository: ListUsersRepository) {
    operator fun invoke() : LiveData<UserDetail>{
        return repository.getUserDetail()
    }
}