package com.niko.githubusers.data.api

import com.niko.githubusers.domain.models.User
import com.niko.githubusers.domain.models.UserDetail
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val TOKEN = "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"
interface githubApi {

    @Headers("Authorization: token $TOKEN")
    @GET("users")
    suspend fun getUsersList(
        @Query("per_page") amount: Int,
        @Query("since") from: Int
    ): Response<List<User>>

    @Headers("Authorization: token $TOKEN")
    @GET("users/{name}")
    suspend fun getUserDetail(@Path("name") name: String): Response<UserDetail>

}

val interceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

val retrofit = Retrofit.Builder().client(client)
    .baseUrl("https://api.github.com")
    .addConverterFactory(GsonConverterFactory.create()).build()
val users_api = retrofit.create(githubApi::class.java)