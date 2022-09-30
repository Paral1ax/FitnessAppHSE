package com.mir.fitnessapplication.api.repository

import com.mir.fitnessapplication.api.instance.RetrofitInstance
import com.mir.fitnessapplication.api.model.User

class UserRepository {

    suspend fun getUser(): User {
        return RetrofitInstance.userApi.getUser()
    }

}