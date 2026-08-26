package com.example.gymwith.domain.repository
// definiuje co robi aplikacja bez uwierzytelniania

import com.example.gymwith.data.remote.dto.AuthResponseDto
import com.example.gymwith.data.remote.dto.LoginRequestDto
import com.example.gymwith.data.remote.dto.RegisterRequestDto

interface AuthRepository{
    suspend fun login(request: LoginRequestDto): Result<AuthResponseDto>
    suspend fun register(request: RegisterRequestDto): Result<AuthResponseDto>
    suspend fun logout()
}