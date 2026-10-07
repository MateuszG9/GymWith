package com.example.gymwith.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymwith.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    // Zmiana email
    fun onEmailChanged(email: String) {
        _uiState.value = _uiState.value.copy(email = email, errorMessage = null)
    }

    // Zmiana hasla
    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(password = password, errorMessage = null)
    }

    // Logowanie
    fun login() {
        val email = _uiState.value.email.trim()
        val password = _uiState.value.password.trim()

        if(email.isEmpty() || password.isEmpty()) {
            _uiState.value =
                _uiState.value.copy(isLoading = true, errorMessage = "Wypełnij wszystkie pola.")
            return
        }
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            val result = authRepository.login(email, password)

            result.onSuccess {
                _uiState.value = _uiState.value.copy(isLoading = false, isSuccess = true)
            }.onFailure { exception ->  _uiState.value = _uiState.value.copy(
                isLoading = false,
                errorMessage = exception.localizedMessage ?: "Złe dane, wprowadź jeszcze raz."
            ) }
        }
    }

    // Rejestracja
    fun register() {
        val email = _uiState.value.email.trim()
        val password = _uiState.value.password.trim()

        if(email.isEmpty() || password.isEmpty()) {
            _uiState.value = _uiState.value.copy(errorMessage = "Wypełnij wszystkie pola.")
            return
        }

        if(password.length < 6) {
            _uiState.value = _uiState.value.copy(errorMessage = "Hasło nie może zawierać mniej niż 6 znaków!")
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            val result = authRepository.register(email, password)

            result.onSuccess {
                _uiState.value = _uiState.value.copy(isLoading = false, isSuccess = true)
            }.onFailure { exception -> _uiState.value = _uiState.value.copy(
                isLoading = false,
                errorMessage = exception.localizedMessage ?: "Błąd rejestracji."
            ) }
        }
    }

    // Resetowanie stanu po nawigacji
    fun resetState() {
        _uiState.value = AuthUiState()
    }
}