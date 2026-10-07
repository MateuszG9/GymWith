package com.example.gymwith.data.remote.dto

// Profil użytkownnika do przechowywania informacji w Cloud Firestore

data class UserProfileDto(
    val uid: String = "",
    val email: String = "",
    val name: String = ""
)