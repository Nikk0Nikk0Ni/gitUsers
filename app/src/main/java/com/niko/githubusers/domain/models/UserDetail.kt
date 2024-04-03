package com.niko.githubusers.domain.models

data class UserDetail(
    val name : String?,
    val avatar_url : String?,
    val email : String?,
    val followers : Int,
    val following : Int,
    val created_at : String
)
