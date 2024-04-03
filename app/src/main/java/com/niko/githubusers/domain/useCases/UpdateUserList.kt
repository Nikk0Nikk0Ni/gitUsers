package com.niko.githubusers.domain.useCases

import com.niko.githubusers.domain.repository.ListUsersRepository

class UpdateUserList(private val repository: ListUsersRepository) {
    suspend operator fun invoke(){
        repository.updateList()
    }
}