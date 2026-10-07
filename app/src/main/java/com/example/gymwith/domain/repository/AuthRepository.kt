package com.example.gymwith.domain.repository
// definiuje co robi aplikacja bez uwierzytelniania

import com.example.gymwith.data.remote.dto.AuthResponseDto
import com.example.gymwith.data.remote.dto.LoginRequestDto
import com.example.gymwith.data.remote.dto.RegisterRequestDto

interface AuthRepository {
    suspend fun login(email: String, pass: String): Result<Unit>
    suspend fun register(email: String, pass: String): Result<Unit>
    suspend fun logout()
    fun isUserLoggedIn(): Boolean
}