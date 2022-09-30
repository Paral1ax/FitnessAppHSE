package com.mir.fitnessapplication.api.service

import retrofit2.http.GET

interface FitnessExercises {
    @GET
    suspend fun getExercises()
}