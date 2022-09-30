package com.mir.fitnessapplication.api.service

import com.mir.fitnessapplication.api.model.Login
import com.mir.fitnessapplication.api.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserClient {
    @POST("login")
    suspend fun login(@Body login: Login) : Call<User>

    @GET("basic")
    suspend fun getUser(@Header("Authorization") authHeader: String): User

    //@GET("secret")
    //fun
}