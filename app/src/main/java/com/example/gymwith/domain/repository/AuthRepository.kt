package com.example.gymwith.domain.repository
// definiuje co robi aplikacja bez uwierzytelniania

interface AuthRepository {
    suspend fun login(email: String, pass: String): Result<Unit>
    suspend fun register(email: String, pass: String): Result<Unit>
    suspend fun logout()
    fun isUserLoggedIn(): Boolean
}