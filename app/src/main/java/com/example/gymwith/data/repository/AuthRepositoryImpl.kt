package com.example.gymwith.data.repository
// implementacja z domain -> łączy komunikację sieciową z lokalnym zapisem sesji

import com.example.gymwith.data.local.TokenManager
import com.example.gymwith.data.remote.api.AuthApiService
import com.example.gymwith.data.remote.dto.AuthResponseDto
import com.example.gymwith.data.remote.dto.LoginRequestDto
import com.example.gymwith.data.remote.dto.RegisterRequestDto
import com.example.gymwith.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val apiService: AuthApiService,
    private val tokenManager: TokenManager
) : AuthRepository{

    override suspend fun login(request: LoginRequestDto): Result<AuthResponseDto>{
        return runCatching{
            val response = apiService.login(request)
            tokenManager.saveToken(response.token)
            response
        }
    }

    override suspend fun register(request: RegisterRequestDto): Result<AuthResponseDto>{
        return runCatching{
            val response = apiService.register(request)
            tokenManager.saveToken(response.token)
            response
        }
    }

    override suspend fun logout(){
        tokenManager.clearToken()
    }
}