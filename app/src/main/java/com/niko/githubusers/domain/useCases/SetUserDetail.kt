package com.niko.githubusers.domain.useCases

import com.niko.githubusers.domain.repository.ListUsersRepository

class SetUserDetail(private val repository : ListUsersRepository) {
    suspend operator fun invoke(userName : String){
        repository.setUserDetail(userName)
    }
}