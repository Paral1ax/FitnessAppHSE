package com.mir.fitnessapplication.api.instance

import com.mir.fitnessapplication.api.service.FitnessExercises
import com.mir.fitnessapplication.api.service.UserClient
import com.mir.fitnessapplication.api.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userApi: UserClient by lazy {
        retrofit.create(UserClient::class.java)
    }

    val fitnessExercises: FitnessExercises by lazy {
        retrofit.create(FitnessExercises::class.java)
    }

}