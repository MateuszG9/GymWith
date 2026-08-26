package com.example.gymwith.data.remote.api
// interfejs biblioteki retrofit -> wysyłanie wiadomości z apki na serwer

import com.example.gymwith.data.remote.dto.AuthResponseDto
import com.example.gymwith.data.remote.dto.LoginRequestDto
import com.example.gymwith.data.remote.dto.RegisterRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService{

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): AuthResponseDto

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequestDto): AuthResponseDto
}