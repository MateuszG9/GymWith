package com.example.gymwith.data.remote.api
// interfejs retrofit operacje danych treningowych komunikujac sie z backend'em w FastAPI

import com.example.gymwith.data.remote.dto.WorkoutPlanDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WorkoutApiService{

    @GET("workouts/plans")
    suspend fun getWorkoutPlans(): List<WorkoutPlanDto>

    @POST("workouts/plans")
    suspend fun createWorkoutPlan(@Body plan: WorkoutPlanDto): WorkoutPlanDto

    @GET("workouts/plans/{id}")
    suspend fun getWorkoutPlanById(@Path("id") id: String): WorkoutPlanDto
}