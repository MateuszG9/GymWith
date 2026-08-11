package com.example.gymwith.data.remote.dto

// Definicja struktury danych wysyłane do backendu i tam odbierane w FastAPI do logowania i rejestracji

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val email: String,
    val password: String
)

@Serializable
data class RegisterRequestDto(
    val email: String,
    val password: String,
    val name: String
)

@Serializable
data class AuthResponseDto(
    val token: String,
    val userID: String,
    val email: String
)