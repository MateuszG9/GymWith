package com.example.gymwith.data.repository
// implementacja z domain -> łączy komunikację sieciową z lokalnym zapisem sesji

import com.google.firebase.auth.FirebaseAuth
import com.example.gymwith.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth) : AuthRepository {

    override suspend fun login(email: String, pass: String): Result<Unit> {
        return runCatching {
            firebaseAuth.signInWithEmailAndPassword(email, pass).await()
            Unit
        }
    }

    override suspend fun register(email: String, pass: String): Result<Unit> {
        return runCatching {
            firebaseAuth.createUserWithEmailAndPassword(email, pass).await()
            Unit
        }
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }

    override fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}